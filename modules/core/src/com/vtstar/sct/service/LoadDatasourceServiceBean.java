package com.vtstar.sct.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haulmont.cuba.core.global.DataManager;
import com.vtstar.sct.entity.VtOPCSignal;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
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
        //导入OPC数据
        loadOPCDatasource();
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

}