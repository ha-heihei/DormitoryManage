package com.lh.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.dao.ScoreMapper;
import com.lh.dao.UserMapper;
import com.lh.services.OfficeServices;
import com.lh.services.UserServices;
import com.lh.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201117 16:23
 */
public class OfficeServicesImpl implements OfficeServices {

    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Gson gson;

    @Override
    public String getAllScoreToOffice(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("data",gson.toJson(scoreMapper.getAllScoreToOffice(map)));
        object.addProperty("num",scoreMapper.getAllScoreSizeToOffice(map));
        return object.toString();
    }

    @Override
    public String getScoreDetail(Map<String, Object> map) {
        return gson.toJson(scoreMapper.getScoreDetail(map));
    }

    @Override
    public String getPreCla(Map<String, Object> map) {
        return gson.toJson(scoreMapper.getPreCla(map));
    }

    @Override
    public String getPreDormitory(Map<String, Object> map) {
        return gson.toJson(scoreMapper.getPreDormitory(map));
    }

    @Override
    public HSSFWorkbook downLoadExcelToOffice(Map<String, Object> map) {
        List<String> cols=new ArrayList<String>();
        cols.add("查宿日期");
        cols.add("分数");
        cols.add("学号");
        cols.add("学生姓名");
        cols.add("辅导员");
        cols.add("宿舍号");
        cols.add("备注");
        cols.add("班级");
        return ExcelUtils.downLoadExcel("查宿分数表",cols,scoreMapper.downLoadExcelToOffice(map));
    }

    @Override
    public String insertStuCards(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        if(userMapper.insertStuCards(map)>0){
            object.addProperty("code",0);
        }
        return gson.toJson(object);
    }
}
