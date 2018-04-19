package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.vtstar.sct.entity.Mqtt;
import com.vtstar.sct.entity.VtApps;
import com.vtstar.sct.entity.VtAppsStatusEnum;
import com.vtstar.sct.utils.MQTTUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

@Service(VtAppsService.NAME)
public class VtAppsServiceBean implements VtAppsService {

    @Inject
    private DataManager dataManager;

    @Inject
    private Persistence persistence;


    @Override
    public List<VtApps> query() {
        LoadContext<VtApps> loadContext = LoadContext.create(VtApps.class).setQuery(
                LoadContext.createQuery("select e from sct$VtApps e order by e.createTs DESC")
        ).setView("vtApps-view");
        return dataManager.loadList(loadContext);
    }

    @Override
    public boolean checkClientState() {
        LoadContext<VtApps> loadContext = LoadContext.create(VtApps.class).setQuery(LoadContext.createQuery("select e from sct$VtApps e order by e.createTs DESC"))
                .setView("vtApps-view");
        List<VtApps> items = dataManager.loadList(loadContext);
        try (Transaction tx = persistence.createTransaction()) {

            for (VtApps item : items) {
                EntityManager entityManager = persistence.getEntityManager();
                boolean isOnline = checkClientState(item.getIp(), item.getPort());

                if (isOnline) {
                    item.setStatus(VtAppsStatusEnum.processing);
                } else {
                    MQTTUtil mqtt = new MQTTUtil("test");
                    mqtt.publish("warning", "警报"+ item.getName() + "下线了");
                    item.setStatus(VtAppsStatusEnum.closed);
                }
                entityManager.merge(item);

            }
            tx.commit();
        }
        return true;
    }


    public boolean checkClientState(String host, int port) {
        Socket s = null;
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(host, port), 2000);

            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (Exception e) {

                }
            }

        }
    }

    @Override
    public List<VtApps> fetch() {
        LoadContext<VtApps> loadContext = LoadContext.create(VtApps.class).setQuery(LoadContext.createQuery("select e from sct$VtApps e order by e.createTs DESC")).setView("vtApps-view");
        return dataManager.loadList(loadContext);
    }

}