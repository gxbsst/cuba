package com.vtstar.sct.service;

import com.vtstar.sct.opc.ConnectionUtil;
import com.vtstar.sct.utils.MQTTUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.log4j.Logger;
import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.*;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
//监听包数
public class OpcBagServiceBean implements Runnable {
    private final static Logger log = Logger.getLogger(OpcBagServiceBean.class);
    private String bags = "";
    private MQTTUtil mqttUtil = new MQTTUtil("TotalBags");
    @Override
    public void run() {
        try {
            bagListener();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    public void bagListener() throws Exception {
        ConnectionInformation ci = ConnectionUtil.getConnectionInformation();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("mixer-thread-pool-%d").daemon(true).build());
        Server server = new Server(
                ci, scheduledExecutorService);
        server.connect();
        Group group = server.addGroup();
        Map<String, Item> items = group.addItems("TotalBags");
        AccessBase access = new Async20Access(server, 1000, false);
        for (Map.Entry<String, Item> temp : items.entrySet()) {
            access.addItem(temp.getValue().getId(), new DataCallback() {
                @Override
                public void changed(Item item, ItemState itemState) {
                    CompletableFuture.supplyAsync(()->{
                        try {
                            if (!bags.equals(itemState.getValue().getObjectAsUnsigned().getValue().toString())){
                                bags = itemState.getValue().getObjectAsUnsigned().getValue().toString();
                                mqttUtil.publish("TotalBags", itemState.getValue().getObjectAsUnsigned().getValue().toString());
                                log.info(item.getId() + "==>" + itemState.getValue().getObjectAsUnsigned().getValue().toString());
                            }
                        } catch (JIException e) {
                            log.error(e.getMessage());
//                            PostUtil.callGet(e.getMessage());
                        }
                        return  null;
                    });
                }
            });
        }
        while(true){
            access.bind();
            Thread.sleep(90000);
            access.unbind();
            server.disconnect();
            server.connect();
        }
    }
}