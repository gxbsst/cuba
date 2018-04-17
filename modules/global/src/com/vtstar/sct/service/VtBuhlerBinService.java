package com.vtstar.sct.service;


import java.math.BigDecimal;

public interface VtBuhlerBinService {
    String NAME = "sct_VtBuhlerBinService";

    boolean updateBinCoverStatus(String canNo, String status);

//    //1楼载体，6楼大料投料之后更新料仓数量
//    String feedingUpdateBuhlerBinQty(String canNo, BigDecimal qty);
//
//    //Buhler系统下料以后通过OPC更新料仓数量
////    String opcUpdateBuhlerBinQty(String itemId, String value, VtWorkOrder workOrder);
//
//    //计算批次投料的总和
//    BigDecimal getTotalQty(String batchNumber);
//
////    //创建配料的称重记录
////    void createVtBuhlerWeighingRecord(BigDecimal qyt, String invCode, VtWorkOrder workOrder, String type, String factoryBatchNo);
//
//    //测试异步导入
//    void testLoadData();
//    void testUpdateBin();
//
//    //更新buhler仓开启状态
//    void updateBinOpen(String canNo);
//
//    void updateBinClose(String canNo);
}