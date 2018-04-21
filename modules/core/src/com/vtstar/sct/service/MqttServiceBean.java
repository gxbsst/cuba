package com.vtstar.sct.service;

import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.vtstar.sct.entity.Mqtt;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;


@Service(MqttService.NAME)
public class MqttServiceBean implements MqttService {
    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Override
    public List<Mqtt> mqtt() {
        LoadContext<Mqtt> loadContext = LoadContext.create(Mqtt.class).setQuery(LoadContext.createQuery("select e from sct$Mqtt e order by e.createTs DESC")).setView("mqtt-view");
        return dataManager.loadList(loadContext);
    }

    @Override
    public Mqtt create(Map params) {
        Mqtt mqtt = metadata.create(Mqtt.class);
        mqtt.setTopic((String) params.get("topic"));
        mqtt.setMessage((String) params.get("message"));
        dataManager.commit(mqtt);
        return mqtt;
    }

    @Override
    public List<Mqtt> query(String params) {
        String[] p = params.split(";");
        List<String> list = Arrays.asList(p);

        LoadContext<Mqtt> loadContext = LoadContext.create(Mqtt.class).setQuery(
                LoadContext.createQuery("select e from sct$Mqtt e where e.topic in :topics order by e.createTs DESC")
                        .setParameter("topics", list)
        )
                .setView("mqtt-view");
        return dataManager.loadList(loadContext);
    }


    @Inject
    private Persistence persistence;

    @Override
    public List<Map> groupByTopic() {
        List<Map> result = new ArrayList<>();
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            Query query = em.createQuery("select e.topic, count(e) as total from sct$Mqtt e group by e.topic");

            List queryResult = query.getResultList();
            for (Object item : queryResult) {
                Object[] itemList = (Object[]) item;
                Map map = new HashMap();
                map.put("topic", itemList[0]);
                map.put("count", itemList[1]);
                result.add(map);
            }
            tx.commit();
        }
        return result;
    }

}