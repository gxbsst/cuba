package com.vt.cuba.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.vt.cuba.entity.Mqtt;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@Service(MqttService.NAME)
public class MqttServiceBean implements MqttService {


    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Override
    public List<Mqtt> mqtt() {
        LoadContext<Mqtt> loadContext = LoadContext.create(Mqtt.class).setQuery(LoadContext.createQuery("select e from cuba$Mqtt e order by e.createTs DESC")).setView("mqtt-view");
        return dataManager.loadList(loadContext);
    }

    @Override
    public Mqtt create(Map params) {
        Mqtt mqtt = metadata.create(Mqtt.class);
        mqtt.setTopic((String) params.get("topic"));
        mqtt.setMessage((String) params.get("message"));
        return dataManager.commit(mqtt);
    }
}