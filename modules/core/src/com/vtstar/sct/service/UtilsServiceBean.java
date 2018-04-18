package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.VtApps;
import com.vtstar.sct.entity.VtAppsStatusEnum;
import com.vtstar.sct.entity.VtClientDeviceInformation;
import com.vtstar.sct.entity.VtOPCSignal;
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

}