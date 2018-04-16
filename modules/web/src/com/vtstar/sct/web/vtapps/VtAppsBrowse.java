package com.vtstar.sct.web.vtapps;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.executors.BackgroundTask;
import com.haulmont.cuba.gui.executors.BackgroundTaskHandler;
import com.haulmont.cuba.gui.executors.BackgroundWorker;
import com.haulmont.cuba.gui.executors.TaskLifeCycle;
import com.vtstar.sct.entity.VtApps;
//import com.vtstar.sct.event.NotificationEvent;
//import com.vtstar.sct.service.ApplicationEventProducerService;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class VtAppsBrowse extends AbstractLookup {

    @Inject
    private GroupTable<VtApps> vtAppsesTable;
    @Inject
    private GroupDatasource<VtApps, UUID> vtAppsesDs;
    @Inject
    private BackgroundWorker backgroundWorker;
    @Inject
    private Logger log;

//    @Inject
//    private ApplicationEventProducerService applicationEventProducerService;

    @Override
    public void ready() {
        super.ready();

        Collection<VtApps> items = vtAppsesDs.getItems();
//        checkClientStateTask(items);
    }

    public void onRefresh(Component source) {
        vtAppsesDs.refresh();
        Collection<VtApps> items = vtAppsesDs.getItems();
//        checkClientStateTask(items);
    }

    // TODO:
    // @国栋验证bat执行调用
    public void onStart(Component source) throws IOException {
        VtApps item = vtAppsesDs.getItem();
        Runtime.
                getRuntime().
                exec("cmd /c start \"\" " + item.getBatPath());
    }

//    private void checkClientStateTask(Collection<VtApps> items) {
//
//        NotificationEvent event = new NotificationEvent();
//
//        BackgroundTask<Object, String[]> task = new BackgroundTask<Object, String[]>(6000, TimeUnit.SECONDS, getFrame()) {
//
//
//            @Override
//            public String[] run(TaskLifeCycle<Object> taskLifeCycle) throws Exception {
//
//                String[] states = new String[items.size()];
//                int index = 0;
//                for (VtApps item : items) {
//                    boolean isOnline = checkClientState(item.getIp(),item.getPort());
//                    if (isOnline) {
//                        states[index] = "在线";
//                    } else {
//                        states[index] = "下线";
//
//                        event.setMessage("【服务下线】" + item.getName() );
//                        applicationEventProducerService.produceApplicationEvent(event);
//                    }
//                    index++;
//                }
//                return states;
//            }
//
//
//            @Override
//            public void done(String[] result) {
//                int index = 0;
//
//                log.info("......" + String.valueOf(result));
//                for (VtApps item : vtAppsesDs.getItems()) {
//                    item.setState(result[index]);
//                    index++;
//                }
//
//                vtAppsesTable.repaint();
//
//            }
//        };
//
//        // Get task handler object and run the task
//        BackgroundTaskHandler taskHandler = backgroundWorker.handle(task);
//        taskHandler.execute();
//    }

//    public boolean checkClientState(String host, int port) {
//        Socket s = null;
//        try {
//            s = new Socket();
//            s.connect(new InetSocketAddress(host, port), 2000);
//
//            return true;
//        } catch (Exception e) {
//            return false;
//        } finally {
//            if (s != null)
//            {
//                try {
//                    s.close();
//                } catch (Exception e) {
//
//                }
//            }
//
//        }
//    }
}