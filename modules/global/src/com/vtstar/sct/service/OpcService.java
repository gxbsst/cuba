package com.vtstar.sct.service;


import java.nio.channels.ClosedChannelException;
import java.util.Map;

public interface OpcService {
    String NAME = "sct_OpcService";

    //修改状态把值变为1在变为0载体和小料消除报警，完成投料
    void changeStatus(String itemId);

    void writeItems(Map<String, String> itemMap);

    void balanceZero();

    //写出到OPC
    void writeOpc(String itemId, String value) throws ClosedChannelException;

    //读取某个itemId的值
    String readOpc(String itemId);

}