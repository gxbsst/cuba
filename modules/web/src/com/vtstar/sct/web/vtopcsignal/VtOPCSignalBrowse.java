package com.vtstar.sct.web.vtopcsignal;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.vtstar.sct.entity.VtOPCSignal;
//import com.vtstar.sct.entity.VtOPCSignal;
//import com.vtstar.sct.service.LoadDatasourceService;

import javax.inject.Inject;
import java.util.UUID;

public class VtOPCSignalBrowse extends AbstractLookup {
    @Inject
    private GroupDatasource<VtOPCSignal, UUID> vtOPCSignalsDs;

//    @Inject
//    private LoadDatasourceService loadDatasourceService;

    /*@Override
    public void init(Map<String, Object> params) {
        String msg = loadDatasourceService.loadOPCDatasource();
        if (msg.equals("Success")) {
            vtOPCSignalsDs.refresh();
        } else {
            showNotification(msg, NotificationType.HUMANIZED);
        }
    }*/
}