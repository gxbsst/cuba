package com.vtstar.sct.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.defaults.DefaultBoolean;
import com.haulmont.cuba.core.config.defaults.DefaultInt;
import com.haulmont.cuba.core.config.defaults.DefaultString;

@Source(type = SourceType.DATABASE)
public interface SctConfig extends Config {
    @Property("sct.desktop.isFullScreen")
    @DefaultBoolean(true)
    boolean getIsFullScreen();

    @Property("sct.desktop.isShowMenu")
    @DefaultBoolean(true)
    boolean getIsShowMenu();

    @Property("sct.desktop.isShowLogo")
    @DefaultBoolean(true)
    boolean getIsShowLogo();

    @Property("sct.desktop.isShowCompanyName")
    @DefaultBoolean(true)
    boolean getIsShowCompanyName();

    @Property("sct.desktop.isShowStatusBar")
    @DefaultBoolean(true)
    boolean getIsShowStatusBar();

    @Property("sct.desktop.printerName")
    @Default("Gprinter GP-3120TL")
    @DefaultString("Gprinter GP-3120TL")
    String getPrinterName();

    @Property("sct.companyName")
    @Default("上海文什数据科技有限公司")
    @DefaultString("上海文什数据科技有限公司")
    String getCompanyName();

    @Property("sct.desktop.isShowTabsPane")
    @DefaultBoolean(true)
    boolean getIsShowTabsPane();

    //获取 IP
    //请求FAP的订单数据、出入库数据访问地址
    @Property("sct.service.httpRequest.host")
    @DefaultString("www.frontan.com")
    String getHttpHost();

    //获取端口号
    //请求FAP的订单数据、出入库数据访问端口
    @Property("sct.service.httpRequest.port")
    @DefaultInt(8081)
    int getHttpPort();

    //获取系统时间
    @Property("sct.service.httpRequest.date")
    @DefaultString("2018-01-03")
    String getHttpDate();

    //获取打印的服务IP
    @Property("sct.service.httpRequest.printHost.fuwu")
    @DefaultString("192.168.10.62")
    String getHttpPrintHostFuwu();

    //获取仓库打印的服务IP
    @Property("sct.service.httpRequest.printHost.cangku")
    @DefaultString("192.168.4.6")
    String getHttpPrintHostCangku();

    //获取打印服务端口
    @Property("sct.service.httpRequest.printPort")
    @DefaultString("8099")
    String getHttpPrintPort();

    //获取打印服务名字
    /*@Property("sct.service.httpRequest.printName.11")
    @DefaultString("ZDesigner 105SLPlus-300dpi ZPL")
    String getHttpPrintName11();*/

    //获取打印服务名字
    /*@Property("sct.service.httpRequest.printName.21")
    @DefaultString("ZDesigner ZM400 300 dpi (ZPL)")
    String getHttpPrintName21();*/

    //获取打印服务名字
    @Property("sct.service.httpRequest.printName.cangku")
    @DefaultString("ZDesigner GT800 (EPL)")
    String getHttpPrintNameCangku();

    //获取 MQTT host
    @Property("sct.service.httpRequest.mqtt.host")
    @DefaultString("tcp://192.168.10.62:1883")
    String getMqttHost();

    @Property("sct.web.defaultScreen")
    @DefaultString("dashboard")
    String getDefaultScreen();

    //是否调用LED
    @Property("sct.desktop.isLEDStart")
    @DefaultBoolean(true)
    boolean getIsLEDStart();

    //是否打开挡板
    @Property("sct.opc.isPlCmdMLock")
    @DefaultBoolean(false)
    boolean getIsPlCmdMLock();

    //是否回写ERP
    @Property("sct.test.isCallBackErp")
    @DefaultBoolean(false)
    boolean getIsCallBackErp();

    @Property("sct.test.isCallBackErpLoad")
    @DefaultBoolean(false)
    boolean getIsCallBackErpLoad();

    //是否使用实际的打印数量
    @Property("sct.service.isActualPrintingQty")
    @DefaultBoolean(false)
    boolean getIsActualPrintingQty();

    @Property("sct.service.isActualPrint")
    @DefaultBoolean(true)
    boolean getIsActualPrint();

    //混合参数设置
    @Property("sct.service.isActualBlendConfig")
    @DefaultBoolean(false)
    boolean getIsActualBlendConfig();

    //新标签管理，判读是否标签审核才可以导入订单
    @Property("sct.service.isNewLabelManagement")
    @DefaultBoolean(false)
    boolean getIsNewLabelManagement();

    // 每次投料，是否将实际投料的数量写入 opc 数据库
    @Property("sct.service.isWriteCanQuantity")
    @DefaultBoolean(false)
    boolean getIsWriteCanQuantity();

    //混合参数设置
    @Property("sct.service.isPublishMQTTWithCheckAppState")
    @DefaultBoolean(false)
    boolean getIsPublishMQTTWithCheckAppState();

}
