package com.vtstar.sct.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.VtClientDeviceInformation;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(VtClientDeviceInformationService.NAME)
public class VtClientDeviceInformationServiceBean implements VtClientDeviceInformationService {

    @Inject
    private DataManager dataManager;

    @Override
    public List<VtClientDeviceInformation> query() {
            LoadContext<VtClientDeviceInformation> loadContext = LoadContext.create(VtClientDeviceInformation.class).setQuery(
                LoadContext.createQuery("select e from sct$VtClientDeviceInformation e")
        );
        return dataManager.loadList(loadContext);
    }
}