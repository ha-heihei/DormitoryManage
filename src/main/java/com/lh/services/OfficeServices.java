package com.lh.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;

/**
 * @author LiHao
 * @create 20201117 16:23
 */
public interface OfficeServices {

    public String getAllScoreToOffice(Map<String,Object> map);

    public String getScoreDetail(Map<String,Object> map);

    public String getPreCla(Map<String,Object> map);

    public String getPreDormitory(Map<String,Object> map);

    public HSSFWorkbook downLoadExcelToOffice(Map<String,Object> map);

    public String insertStuCards(Map<String,Object> map);

}
