package com.lh.dao;

import com.lh.dto.ScoreAndStudent;
import com.lh.dto.ScoreAndUser;
import com.lh.pojo.Score;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 10:53
 */
public interface ScoreMapper {

    public int insertScore(Score score);

    public List<ScoreAndUser> getScoreList(Map<String,Object> map);

    public int getScoreListCount(Map<String,Object> map);

    public int updateScore(Map<String,Object> map);

    public List<ScoreAndStudent> getScoreChartsByCla(Map<String,Object> map);

    public List<Map<String,Object>> getScoreListByCla(Map<String,Object> map);

    public List<Map<String,Object>> getAllScoreToOffice(Map<String,Object> map);

    public int getAllScoreSizeToOffice(Map<String,Object> map);

    public List<Map<String,Object>> downLoadScoreListByDate(Map<String,Object> map);

    public List<Map<String,Object>> getScoreDetail(Map<String,Object> map);

    public List<Map<String,Object>> getPreCla(Map<String,Object> map);

    public List<Map<String,Object>> getPreDormitory(Map<String,Object> map);

    public List<Map<String,Object>> downLoadExcelToOffice(Map<String,Object> map);

    public List<Map<String,Object>> queryAllScore(Map<String,Object> map);

    public Map<String,Object> queryDorScore(Map<String,Object> map);

    public List<Map<String,Object>> queryPageDorScore(Map<String,Object> map);

    public int queryPageDorScoreCount(Map<String,Object> map);


    //    导员查看进度
    public int queryNowDorCount(Map<String,Object> map);

    public int queryNowDorAllCount(Map<String,Object> map);
//    导员查看结束


    //    学生处查看进度
    public int queryNowAllDorCount(Map<String,Object> map);

    public int queryDorAllCount();

//    学生处查看进度结束

}
