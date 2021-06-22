package com.lh.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.dao.QNMapper;
import com.lh.services.QNServices;
import com.lh.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201203 19:15
 */
public class QNServicesImpl implements QNServices {

    public void setQnMapper(QNMapper qnMapper) {
        this.qnMapper = qnMapper;
    }

    @Autowired
    private QNMapper qnMapper;

    @Autowired
    private Gson gson;

    public String insertQN(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        if(qnMapper.insertQN(map)>0){
            object.addProperty("code",0);
        }
        return gson.toJson(object);
    }

    public String queryQN(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("data",gson.toJson(qnMapper.queryQN(map)));
        object.addProperty("num",qnMapper.queryQNCount(map));
        return gson.toJson(object);
    }

    public HSSFWorkbook downLoadQN(Map<String, Object> map) {
        List<String> cols=new ArrayList<String>();
        cols.add("日期");
        cols.add("中午温度");
        cols.add("学号");
        cols.add("性别");
        cols.add("姓名");
        cols.add("班级");
        cols.add("早晨温度");
        return ExcelUtils.downLoadExcel("体温表",cols,qnMapper.downLoadQN(map));
    }
}
