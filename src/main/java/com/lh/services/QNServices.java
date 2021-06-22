package com.lh.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;

/**
 * @author LiHao
 * @create 20201203 19:14
 */
public interface QNServices {

    public String insertQN(Map<String,Object> map);

    public String queryQN(Map<String,Object> map);

    public HSSFWorkbook downLoadQN(Map<String,Object> map);

}
