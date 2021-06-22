package com.lh.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.dao.ScoreMapper;
import com.lh.pojo.Score;
import com.lh.services.ScoreServices;
import com.lh.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 10:59
 */
public class ScoreServicesImpl implements ScoreServices {

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private Gson gson;

    public void setScoreMapper(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public int insertScore(Score score) {
        return scoreMapper.insertScore(score);
    }

    @Override
    public String getScoreList(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("data",gson.toJson(scoreMapper.getScoreList(map)));
        object.addProperty("num",scoreMapper.getScoreListCount(map));
        return gson.toJson(object);
    }

    @Override
    public String updateScore(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        if(scoreMapper.updateScore(map)>0){
            object.addProperty("code",0);
        }
        return gson.toJson(object);
    }

    @Override
    public String getScoreChartsByCla(Map<String,Object> map) {
        return gson.toJson(scoreMapper.getScoreChartsByCla(map));
    }

    @Override
    public String getScoreListByCla(Map<String, Object> map) {
        return gson.toJson(scoreMapper.getScoreListByCla(map));
    }

    @Override
    public HSSFWorkbook downLoadScoreListByDate(Map<String, Object> map) {
        List<String> cols=new ArrayList<String>();
        cols.add("查宿日期");
        cols.add("分数");
        cols.add("学号");
        cols.add("学生姓名");
        cols.add("辅导员");
        cols.add("宿舍号");
        cols.add("备注");
        cols.add("班级");
        return ExcelUtils.downLoadExcel("查宿分数表",cols,scoreMapper.downLoadScoreListByDate(map));
    }

    @Override
    public String queryAllScore(Map<String, Object> map) {
        return gson.toJson(scoreMapper.queryAllScore(map));
    }

    @Override
    public String queryDorScore(Map<String, Object> map) {
        return gson.toJson(scoreMapper.queryDorScore(map));
    }

    @Override
    public String queryPageDorScore(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("data",gson.toJson(scoreMapper.queryPageDorScore(map)));
        object.addProperty("num",gson.toJson(scoreMapper.queryPageDorScoreCount(map)));
        return gson.toJson(object);
    }

    @Override
    public Float queryProgressToInstructor(Map<String, Object> map) {
        int now=scoreMapper.queryNowDorCount(map);
        int all=scoreMapper.queryNowDorAllCount(map);
        return Float.parseFloat(String.valueOf(now))/Float.parseFloat(String.valueOf(all));
    }

    @Override
    public Float queryProgressToOffice(Map<String, Object> map) {
        int now=scoreMapper.queryNowAllDorCount(map);
        int all=scoreMapper.queryDorAllCount();
        return Float.parseFloat(String.valueOf(now))/Float.parseFloat(String.valueOf(all));
    }


}
