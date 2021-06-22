package com.lh;

import com.google.gson.Gson;
import com.lh.dao.ScoreMapper;
import com.lh.utils.ExcelUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201124 10:24
 */
public class MyTest {

    @Test
    public void test(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        ScoreMapper scoreMapper = context.getBean("scoreMapper", ScoreMapper.class);
        List<Map<String, Object>> maps = scoreMapper.downLoadScoreListByDate(new HashMap<String, Object>());
        System.out.println(new Gson().toJson(maps));
        List<String> cols=new ArrayList<String>();
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        cols.add("1");
        

    }

}
