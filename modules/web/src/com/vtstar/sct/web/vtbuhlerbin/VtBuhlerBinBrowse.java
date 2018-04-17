package com.vtstar.sct.web.vtbuhlerbin;

import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.vtstar.sct.entity.VtBuhlerBin;
import com.vtstar.sct.service.LoadDatasourceService;
import com.vtstar.sct.service.VtBuhlerBinService;

import javax.inject.Inject;
import java.util.UUID;

public class VtBuhlerBinBrowse extends AbstractLookup {
    @Inject
    private GroupDatasource<VtBuhlerBin, UUID> vtBuhlerBinsDs;

    @Inject
    private GroupTable<VtBuhlerBin> vtBuhlerBinsTable;

    @Inject
    private Button unbundlingRfid;

    @Inject
    private LoadDatasourceService loadDatasourceService;

    @Inject
    private VtBuhlerBinService vtBuhlerBinService;

    public void loadDs() {
        loadDatasourceService.loadBuhlerBinDatasource();
        vtBuhlerBinsDs.refresh();
        showNotification("导入数据成功！", NotificationType.HUMANIZED);
    }
}