package com.vtstar.sct.web.vtclientdeviceinformation;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.executors.BackgroundWorker;
import com.vtstar.sct.entity.VtClientDeviceInformation;
import com.vtstar.sct.service.ApplicationEventProducerService;
import com.vtstar.sct.service.LoadDatasourceService;
import com.vtstar.sct.service.UtilsService;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.UUID;

public class VtClientDeviceInformationBrowse extends AbstractLookup {

    @Inject
    private GroupDatasource<VtClientDeviceInformation, UUID> vtClientDeviceInformationsDs;

    @Inject
    private GroupTable<VtClientDeviceInformation> vtClientDeviceInformationsTable;

    @Inject
    private BackgroundWorker backgroundWorker;

    @Inject
    private UtilsService utilsService;

    @Inject
    private Logger log;
    @Inject
    private ApplicationEventProducerService applicationEventProducerService;
    @Inject
    private LoadDatasourceService loadDatasourceService;

    @Override
    public void ready() {
        super.ready();
//        Collection<VtClientDeviceInformation> items = vtClientDeviceInformationsDs.getItems();
//        checkClientStateTask(items);
    }

//    private void checkClientStateTask(Collection<VtClientDeviceInformation> items) {
//        BackgroundTask<Object, String[]> task = new BackgroundTask<Object, String[]>(6000, TimeUnit.SECONDS, getFrame()) {
//
//
//            @Override
//            public String[] run(TaskLifeCycle<Object> taskLifeCycle) throws Exception {
//
//                String[] states = new String[items.size()];
//                int index = 0;
//                for (VtClientDeviceInformation item : items) {
//                    boolean isOnline = utilsService.checkClientStateByPing(item.getIp());
//                    if (isOnline) {
//                        states[index] = "在线";
//                    } else {
//                        states[index] = "下线";
//                    }
//                    index++;
//                }
//                return states;
//            }
//
//            @Override
//            public void done(String[] result) {
//                int index = 0;
//
//                log.info("......" + String.valueOf(result));
//                for (VtClientDeviceInformation item : vtClientDeviceInformationsDs.getItems()) {
//                    item.setState(result[index]);
//                    index++;
//                }
//
//                vtClientDeviceInformationsTable.repaint();
//
//            }
//        };
//
//        // Get task handler object and run the task
//        BackgroundTaskHandler taskHandler = backgroundWorker.handle(task);
//        taskHandler.execute();
//    }

//    public void onRefresh(Component source) {
//        vtClientDeviceInformationsDs.refresh();
//        Collection<VtClientDeviceInformation> items = vtClientDeviceInformationsDs.getItems();
//        checkClientStateTask(items);
//    }

    public void loadDs() {
        loadDatasourceService.loadClientDevice();
        showNotification("导入数据成功！", NotificationType.HUMANIZED);
    }
}