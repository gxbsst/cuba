package com.vtstar.sct.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haulmont.cuba.core.global.DataManager;
import com.vtstar.sct.entity.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

@Service(LoadDatasourceService.NAME)
public class LoadDatasourceServiceBean implements LoadDatasourceService {

    @Inject
    private DataManager dataManager;

    @Inject
    private UtilsService utilsService;

    private String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }

    @Override
    public void loadDatasource() {


//        loadAppsDatasource(apps);

        //导入OPC数据
//        loadOPCDatasource();
    }

    //导入OPCSignal数据
    @Override
    public String loadOPCDatasource() {
        String msg = "";
        File file = new File("./opcDataSource.json");
        String JsonContext = null;
        try {
            JsonContext = ReadFile(file.getCanonicalPath());
            if (!"".equals(JsonContext)) {
                Map mapTypes = JSON.parseObject(JsonContext);
                JSONArray OPCSignal = JSONArray.parseArray(mapTypes.get("OPCSignal").toString());
                //导入OPCSignal数据
                loadOPCSignal(OPCSignal);
                msg = "Success";
            } else {
                msg = "导入失败，不存在OPC数据。";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //导入OPCSignal数据
    private void loadOPCSignal(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (utilsService.queryOPCSignal(jsonObject.get("itemId").toString()) == null) {
                VtOPCSignal vtOPCSignal = new VtOPCSignal();
                vtOPCSignal.setItemId(jsonObject.get("itemId").toString());
                if (jsonObject.get("description") != "") {
                    vtOPCSignal.setDescription(jsonObject.get("description").toString());
                }
                vtOPCSignal.setDataType(jsonObject.get("dataType").toString());
                vtOPCSignal.setQuality("Good");
                vtOPCSignal.setLastUpdateTime(new Date());
                dataManager.commit(vtOPCSignal);
            }
        }
    }

    //导入Apps数据
    @Override
    public String loadAppsDatasource() {
        String msg = "";
        File file = new File("./loadDatasource.json");
        String JsonContext = null;
        try {
            JsonContext = ReadFile(file.getCanonicalPath());
            Map mapTypes = JSON.parseObject(JsonContext);
            JSONArray jsonArray = JSONArray.parseArray(mapTypes.get("apps").toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                VtApps apps = new VtApps();
                apps.setName(jsonObject.get("name").toString());
                apps.setIp(jsonObject.get("ip").toString());
                apps.setPort(Integer.parseInt(jsonObject.get("port").toString()));
                dataManager.commit(apps);
            }
            msg = "success";
        } catch (IOException e) {
            msg = "导入失败，不存在数据。";
            e.printStackTrace();
        }
        return msg;
    }

    //导入Apps数据
    @Override
    public String loadBuhlerBinDatasource() {
        String msg = "";
        File file = new File("./loadDatasource.json");
        String JsonContext = null;
        try {
            JsonContext = ReadFile(file.getCanonicalPath());
            Map mapTypes = JSON.parseObject(JsonContext);
            JSONArray jsonArray = JSONArray.parseArray(mapTypes.get("buhlerBin").toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                VtBuhlerBin vtBuhlerBin = new VtBuhlerBin();
                vtBuhlerBin.setCanNo(jsonObject.get("canNo").toString());
                vtBuhlerBin.setCanName(jsonObject.get("canName").toString());
                vtBuhlerBin.setBinlm(jsonObject.get("binlm").toString());
                vtBuhlerBin.setBinWeight(jsonObject.get("binWeight").toString());
                if (jsonObject.get("type").toString().equals("Carrier_Material_Bin")) {
                    vtBuhlerBin.setType(VtBuhlerBinTypeEnum.Carrier_Material_Bin);
                } else {
                    vtBuhlerBin.setType(VtBuhlerBinTypeEnum.Large_Material_Bin);
                }
//                if (getMaterialInfo(jsonObject.get("invCode").toString()) != null) {
//                    vtBuhlerBin.setInvCode(getMaterialInfo(jsonObject.get("invCode").toString()));
//                }
                vtBuhlerBin.setInvCode(jsonObject.get("invCode").toString());
                DecimalFormat df = new DecimalFormat("######0.000");
                double aQty = Double.parseDouble(jsonObject.get("canCapacity").toString());//仓容量
                double bQty = Double.parseDouble(jsonObject.get("actualRemainingQuantity").toString());//实际剩余
                double cQty = Double.parseDouble(jsonObject.get("maxQuantity").toString());//理论最大
                double dQty = Double.parseDouble(jsonObject.get("nominalRemainingQuantity").toString());//理论最小
                double eQty = Double.parseDouble(jsonObject.get("errorRemainingQuantity").toString());//误差
                vtBuhlerBin.setCanCapacity(new BigDecimal(df.format(aQty)));
                vtBuhlerBin.setActualRemainingQuantity(new BigDecimal(df.format(bQty)));
                vtBuhlerBin.setMaxQuantity(new BigDecimal(df.format(cQty)));
                vtBuhlerBin.setNominalRemainingQuantity(new BigDecimal(df.format(dQty)));
                vtBuhlerBin.setErrorRemainingQuantity(new BigDecimal(df.format(eQty)));
                vtBuhlerBin.setOpened((boolean) jsonObject.get("opened"));
                vtBuhlerBin.setStatus(VtBuhlerBinStateEnum.close);
                dataManager.commit(vtBuhlerBin);
                //测试
//                batchManagementService.createBatchManageByBuhlerBin(vtBuhlerBin);
            }
            msg = "success";
        } catch (IOException e) {
            msg = "导入失败，不存在数据。";
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void loadRfidConfigData() {
        File file = new File("./loadDatasource.json");
        String JsonContext = null;
        try {
            JsonContext = ReadFile(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map mapTypes = JSON.parseObject(JsonContext);
        JSONArray rfidPort = JSON.parseArray(mapTypes.get("ports").toString());
        JSONArray rfidImpinj = JSON.parseArray(mapTypes.get("impinj").toString());
//        JSONArray rfidConfigInformation = JSON.parseArray(mapTypes.get("rfidConfigInformation").toString());

        //导入天线标识配置
        loadRfidPort(rfidPort);

        //导入设备ip配置
        loadRfidImpinj(rfidImpinj);

        //导入天线强度总配置
//        loadRfidConfigInformation(rfidConfigInformation);
    }

    private void loadRfidPort(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            RfidPort rfidPort = new RfidPort();
            rfidPort.setName((String) jsonObject.get("name"));
            rfidPort.setAntenna((Integer) jsonObject.get("antenna"));
            rfidPort.setValid_rssi((Integer) jsonObject.get("valid_rssi"));
            dataManager.commit(rfidPort);
        }
    }

    private void loadRfidImpinj(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            RfidImpinj rfidImpinj = new RfidImpinj();
            rfidImpinj.setIp(jsonObject.get("ip").toString());
            rfidImpinj.setName(jsonObject.get("name").toString());
            rfidImpinj.setSpecId(jsonObject.get("specId").toString());
//            rfidImpinj.setPort(rfidConfigService.queryPort(jsonObject.get("name").toString()));
            dataManager.commit(rfidImpinj);
        }
    }

    //导入ClientDevice数据
    @Override
    public void loadClientDevice() {
        File file = new File("./loadDatasource.json");
        String JsonContext = null;
        try {
            JsonContext = ReadFile(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map mapTypes = JSON.parseObject(JsonContext);

        JSONArray jsonArray = JSONArray.parseArray(mapTypes.get("clientDevice").toString());

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            VtClientDeviceInformation vtClientDeviceInformation = new VtClientDeviceInformation();
            vtClientDeviceInformation.setIp(jsonObject.get("ip").toString());
            vtClientDeviceInformation.setName(jsonObject.get("name").toString());
//            vtClientDeviceInformation.setStatus(jsonObject.get("status").toString());
            if (jsonObject.get("type").toString().equals("PC_Device")) {
                vtClientDeviceInformation.setType(VtClientDeviceTypeEnum.PC_Device);
            } else if (jsonObject.get("type").toString().equals("RFID_Device")) {
                vtClientDeviceInformation.setType(VtClientDeviceTypeEnum.RFID_Device);
            }
            if (jsonObject.get("lineCode") != null) {
                if (jsonObject.get("lineCode").toString().equals("LINE_11")) {
                    vtClientDeviceInformation.setLineCode(VtClientLineCodeEnum.LINE_11);
                } else if (jsonObject.get("lineCode").toString().equals("LINE_12")) {
                    vtClientDeviceInformation.setLineCode(VtClientLineCodeEnum.LINE_12);
                } else if (jsonObject.get("lineCode").toString().equals("LINE_21")) {
                    vtClientDeviceInformation.setLineCode(VtClientLineCodeEnum.LINE_21);
                }
            }
            switch (jsonObject.get("code").toString()) {
                case "weighting":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_WEIGHTING);
                    break;
                case "feeding_3f":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_3F_FEEDING);
                    break;
                case "feeding_6f":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_6F_FEEDING);
                    break;
                case "feeding_1f":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_1F_FEEDING);
                    break;
                case "packing_1f":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_1F_PACKING);
                    break;
                case "wms":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.WMS);
                    break;
                case "FeedingLine12":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_12_FEEDING);
                    break;
                case "PackingLine12":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_12_PACKING);
                    break;
                case "WeightingSmallLine12":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_12_SMALL_WEIGHTING);
                    break;
                case "WeightingBigLine12":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_12_BIG_WEIGHTING);
                    break;
                case "FeedingLine21":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_21_FEEDING);
                    break;
                case "PackingLine21":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_21_PACKING);
                    break;
                case "WeightingSmallLine21":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_21_SMALL_WEIGHTING);
                    break;
                case "WeightingBigLine21":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_21_BIG_WEIGHTING);
                    break;
                case "ManufacturingCenter":
                    vtClientDeviceInformation.setCode(VtClientCodeEnum.LINE_11_MANUFACTURING_CENTER);
                    break;
            }
            dataManager.commit(vtClientDeviceInformation);
        }
    }
}