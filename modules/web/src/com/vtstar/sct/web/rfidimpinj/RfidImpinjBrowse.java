package com.vtstar.sct.web.rfidimpinj;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.vtstar.sct.service.LoadDatasourceService;

import javax.inject.Inject;

public class RfidImpinjBrowse extends AbstractLookup {
    @Inject
    private LoadDatasourceService loadDatasourceService;

    public void loadDs() {
        loadDatasourceService.loadRfidConfigData();
        showNotification("导入数据成功！", NotificationType.HUMANIZED);
    }
}