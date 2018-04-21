package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.vtstar.sct.entity.Mqtt;
import com.vtstar.sct.entity.VtApps;
import com.vtstar.sct.entity.VtAppsStatusEnum;
import com.vtstar.sct.entity.VtOPCSignal;
import com.vtstar.sct.opc.ConnectionUtil;
import com.vtstar.sct.utils.MQTTUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.common.NotConnectedException;
import org.openscada.opc.lib.da.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.UnknownHostException;
import java.nio.channels.ClosedChannelException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service(OpcService.NAME)
public class OpcServiceBean implements OpcService {
//    @Inject
//    private VtExceptionHandlerService vtExceptionHandlerService;

    //修改状态把值变为1在变为0载体和小料消除报警，完成投料
    @Override
    public void changeStatus(String itemId) {
        try {
            writeOpc(itemId, "1");
            Thread.sleep(2000);
            writeOpc(itemId, "0");
        } catch (InterruptedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (ClosedChannelException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        }
    }

    @Override
    public void writeItems(Map<String, String> itemMap) {
        ConnectionInformation ci = ConnectionUtil.getConnectionInformation();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("opcWrite-schedule-pool-%d").daemon(true).build());
        Server server = new Server(
                ci, scheduledExecutorService);
        try {
            server.connect();
            Group group = server.addGroup();
            for (String itemId : itemMap.keySet()) {
                Item item = group.addItem(itemId);
                JIVariant jiVariant = new JIVariant(itemMap.get(itemId));
                item.write(jiVariant);
            }
            server.disconnect();
        } catch (UnknownHostException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (JIException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AlreadyConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AddFailedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (NotConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (DuplicateGroupException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        }


    }

    @Override
    public void balanceZero() {
        ConnectionInformation ci = ConnectionUtil.getConnectionInformation();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("opcWrite-schedule-pool-%d").daemon(true).build());
        Server server = new Server(
                ci, scheduledExecutorService);

        try {
            server.connect();
            Group group = server.addGroup();
            Map<String, Item> items = group.addItems("Scale11_zero", "Scale12_zero", "Scale13_zero", "Scale14_zero", "Micro11_zero");
            for (String itemId : items.keySet()) {
                Item item = group.addItem(itemId);
                JIVariant jiVariant = new JIVariant("1");
                item.write(jiVariant);
            }
            Thread.sleep(3000);
            for (String itemId : items.keySet()) {
                Item item = group.addItem(itemId);
                JIVariant jiVariant = new JIVariant("0");
                item.write(jiVariant);
            }
            server.disconnect();
        } catch (UnknownHostException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (JIException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AlreadyConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AddFailedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (DuplicateGroupException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (NotConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        }


    }

    //写出到OPC
    @Override
    public void writeOpc(String itemId, String value) throws ClosedChannelException {
        ConnectionInformation ci = ConnectionUtil.getConnectionInformation();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("opcWrite-schedule-pool-%d").daemon(true).build());
        Server server = new Server(
                ci, scheduledExecutorService);

        try {
            server.connect();
            Group group = server.addGroup();
            Item item = group.addItem(itemId);
            JIVariant jiVariant = new JIVariant(value);
            item.write(jiVariant);
            server.disconnect();
        } catch (UnknownHostException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (JIException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AlreadyConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AddFailedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (NotConnectedException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (DuplicateGroupException e) {
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        }


    }

    //读取某个itemId的值
    @Override
    public String readOpc(String itemId) {
        ConnectionInformation ci = ConnectionUtil.getConnectionInformation();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("readOpc-schedule-pool-%d").daemon(true).build());
        Server server = new Server(ci, scheduledExecutorService);
        try {
            server.connect();
            Group group = server.addGroup();
            Item item = group.addItem(itemId);
            ItemState itemState = item.read(true);
            if (17 <= item.read(false).getValue().getType()) {
                //byte类型返回
                return itemState.getValue().getObjectAsUnsigned().getValue().toString();
                //转换String类型8
            } else if (item.read(false).getValue().getType() == 8) {
                return itemState.getValue().getObjectAsString().getString();
            } else {
                //boolean类型
                return itemState.getValue().toString().substring(2, itemState.getValue().toString().length() - 2);
            }
        } catch (UnknownHostException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (JIException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AlreadyConnectedException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (AddFailedException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (NotConnectedException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        } catch (DuplicateGroupException e) {
            System.out.printf(String.valueOf(e));
//            vtExceptionHandlerService.creatExceptionNotification("OPC Service", e.getMessage());
        }
        return null;
    }

    @Inject
    private Metadata metadata;

    @Inject
    private DataManager dataManager;

    @Inject
    private Persistence persistence;

    @Override
    public VtOPCSignal update(Map params) {
        String topic = (String) params.get("topic");
        String itemId = topic.split("/")[1];
        String value = (String) params.get("message");
        LoadContext<VtOPCSignal> loadContext = LoadContext.create(VtOPCSignal.class)
                .setQuery(LoadContext
                        .createQuery("select e from sct$VtOPCSignal e where e.itemId=:itemId").setParameter("itemId", itemId));
        VtOPCSignal item = dataManager.load(loadContext);
        if (item != null) {
            try (Transaction tx = persistence.createTransaction()) {
                EntityManager entityManager = persistence.getEntityManager();
                item.setValue(value);
                entityManager.merge(item);
                tx.commit();
            }
        }
        return item;
    }

}
