package com.vtstar.sct.service;

public interface LoadDatasourceService {
    String NAME = "sct_LoadDatasourceService";

    void loadDatasource();

//    void loadRfidConfigData();

    //导入OPCSignal数据
    String loadOPCDatasource();

    //导入包装规格信息
    //String loadPackageSpecDatasource();

    //产品标签信息
    //String loadProductLabelDatasource();

}