package com.vt.cuba.service;


import com.vt.cuba.entity.Mqtt;

import java.util.List;
import java.util.Map;


public interface MqttService {
    String NAME = "cuba_MqttService";

    /**
     * @return
     */
    List<Mqtt> mqtt();

    Mqtt create(Map params);
}