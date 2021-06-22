package com.lh.services;

import com.lh.pojo.Score;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 10:58
 */
public interface ScoreServices {

    public int insertScore(Score score);

    public String getScoreList(Map<String,Object> map);

    public String updateScore(Map<String,Object> map);

    public String getScoreChartsByCla(Map<String,Object> map);

    public String getScoreListByCla(Map<String,Object> map);

    public HSSFWorkbook downLoadScoreListByDate(Map<String,Object> map);

    public String queryAllScore(Map<String,Object> map);

    public String queryDorScore(Map<String,Object> map);

    public String queryPageDorScore(Map<String,Object> map);

    public Float queryProgressToInstructor(Map<String,Object> map);

    public Float queryProgressToOffice(Map<String,Object> map);

}
