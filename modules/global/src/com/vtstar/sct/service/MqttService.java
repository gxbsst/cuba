package com.vtstar.sct.service;


import com.vtstar.sct.entity.Mqtt;

import java.util.List;
import java.util.Map;

public interface MqttService {
    String NAME = "sct_MqttService";
    public Mqtt create(Map params);
    public List<Mqtt> mqtt();

    List<Mqtt> query(String params);
}