package com.vtstar.sct.web.vtopcsignal;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.vtstar.sct.entity.VtOPCSignal;
import com.vtstar.sct.entity.VtOPCSignalUpdateLog;

import javax.inject.Inject;
import java.util.UUID;

public class VtOPCSignalEdit extends AbstractEditor<VtOPCSignal> {

    @Inject
    private Datasource<VtOPCSignal> vtOPCSignalDs;
    @Inject
    private GroupDatasource<VtOPCSignalUpdateLog, UUID> vtOPCSignalUpdateLogsDs;


}