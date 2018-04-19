package com.vtstar.sct.service;

import com.haulmont.cuba.core.entity.Config;
import com.vtstar.sct.entity.VtOPCSignal;

import java.util.List;

public interface UtilsService {
    String NAME = "sct_UtilsService";

    // 根据 itemId 查询 是否存在OPC信号信息
    VtOPCSignal queryOPCSignal(String itemId);
    
    boolean checkClientStateByPing();

    // 获取SCT Config
    Config getSctConfig(String name);

    List<Config> getSctConfig();
}