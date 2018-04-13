package com.vtstar.sct.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.RfidImpinj;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(RfidService.NAME)
public class RfidServiceBean implements RfidService {
    @Inject
    private DataManager dataManager;

    @Override
    public List<RfidImpinj> rfid() {
        LoadContext<RfidImpinj> loadContext = LoadContext.create(RfidImpinj.class).setQuery(LoadContext.createQuery("select e from sct$RfidImpinj e order by e.createTs DESC")).setView("rfidImpinj-with-port-view");
        return dataManager.loadList(loadContext);
    }
}