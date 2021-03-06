package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.entity.Config;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.net.InetAddress;
import java.util.List;

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

    @Inject
    private Persistence persistence;

    @Override
    public boolean checkClientStateByPing() {
        LoadContext<VtClientDeviceInformation> loadContext = LoadContext.create(VtClientDeviceInformation.class).setQuery(LoadContext.createQuery("select e from sct$VtClientDeviceInformation e order by e.createTs DESC"));

        List<VtClientDeviceInformation> items = dataManager.loadList(loadContext);
        try (Transaction tx = persistence.createTransaction()) {

            for (VtClientDeviceInformation item : items) {
                EntityManager entityManager = persistence.getEntityManager();

                try {
                    InetAddress address = InetAddress.getByName(item.getIp());
                    boolean isOnline = address.isReachable(5000);
                    if (isOnline) {
                        item.setStatus(VtAppsStatusEnum.processing);
                    } else {
                        item.setStatus(VtAppsStatusEnum.closed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                entityManager.merge(item);

            }
            tx.commit();
        }
        return true;

    }

    @Override
    public Config getSctConfig(String name) {
        LoadContext<Config> context = LoadContext.create(Config.class).setQuery(
                LoadContext.createQuery("select e from sys$Config e where e.name =:name").setParameter("name", name));

        return dataManager.load(context);
    }

    @Override
    public List<Config> getSctConfig() {
        LoadContext<Config> context = LoadContext.create(Config.class).setQuery(
                LoadContext.createQuery("select e from sys$Config e"));
        return dataManager.loadList(context);
    }

    //根据 binlm 查询布勒仓信息
    @Override
    public VtBuhlerBin queryBuhlerBin(String binlmOrBinWeight) {
        if (binlmOrBinWeight.contains("binlm")) {
            LoadContext<VtBuhlerBin> item = LoadContext.create(VtBuhlerBin.class).setQuery(
                    LoadContext.createQuery("select e from sct$VtBuhlerBin e where e.binlm = :binlm")
                            .setParameter("binlm", binlmOrBinWeight)).setView("vtBuhlerBin-with-material-view");
            if (dataManager.loadList(item).size() < 1) {
                return null;
            } else {
                return dataManager.load(item);
            }
        } else {
            LoadContext<VtBuhlerBin> item = LoadContext.create(VtBuhlerBin.class).setQuery(
                    LoadContext.createQuery("select e from sct$VtBuhlerBin e where e.binWeight=:binWeight")
                            .setParameter("binWeight", binlmOrBinWeight));
            if (dataManager.loadList(item).size() < 1) {
                return null;
            } else {
                return dataManager.load(item);
            }
        }
    }
}