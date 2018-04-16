package com.vtstar.sct.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.VtOPCSignal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(UtilsService.NAME)
public class UtilsServiceBean implements UtilsService {

    @Inject
    private DataManager dataManager;

    //根据 itemId 查询 是否存在OPC信号信息
    @Override
    public VtOPCSignal queryOPCSignal(String itemId) {
        LoadContext<VtOPCSignal> opcSignalLoadContext = LoadContext.create(VtOPCSignal.class).setQuery(
                LoadContext.createQuery("select e from sct$VtOPCSignal e where e.itemId = :itemId")
                        .setParameter("itemId", itemId));
        if (dataManager.loadList(opcSignalLoadContext).size() < 1) {
            return null;
        } else {
            return dataManager.loadList(opcSignalLoadContext).get(0);
        }
    }



}