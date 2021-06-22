package com.lh.controller;

import com.lh.services.OfficeServices;
import com.lh.services.ScoreServices;
import com.lh.services.UserServices;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201117 16:21
 */
@Controller
public class OfficeController {

    @Autowired
    private OfficeServices officeServices;

    @Autowired
    private UserServices userServices;

    @Autowired
    private ScoreServices scoreServices;


    @RequestMapping(value = "/getAllScoreToOffice")
    @ResponseBody
    public String getAllScoreToOffice(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt(map.get("start").toString()));
        map.put("end",Integer.parseInt(map.get("end").toString()));
        return officeServices.getAllScoreToOffice(map);
    }

    @RequestMapping(value = "/getScoreDetail")
    @ResponseBody
    public String getScoreDetail(@RequestParam Map<String,Object> map){
        return officeServices.getScoreDetail(map);
    }

    @RequestMapping(value = "/getPreCla")
    @ResponseBody
    public String getPreCla(@RequestParam Map<String,Object> map){
        return officeServices.getPreCla(map);
    }

    @RequestMapping(value = "/getPreDormitory")
    @ResponseBody
    public String getPreDormitory(@RequestParam Map<String,Object> map){
        return officeServices.getPreDormitory(map);
    }

    @RequestMapping(value = "/downLoadExcelToOffice")
    @ResponseBody
    public void downLoadExcelToOffice(@RequestParam Map<String,Object> map, HttpServletResponse response) throws IOException {
        System.out.println("map = " + map);
        String fileName= new String("查宿信息表.xls".getBytes("UTF-8"),"iso-8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        HSSFWorkbook workbook=officeServices.downLoadExcelToOffice(map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @RequestMapping(value = "/getStuCardsList")
    @ResponseBody
    public String getStuCardsList(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt(map.get("start").toString()));
        map.put("end",Integer.parseInt(map.get("end").toString()));
        return userServices.getStuCardsList(map);
    }

    @RequestMapping(value = "/getStuCardsInfo")
    @ResponseBody
    public String getStuCardsInfo(@RequestParam Map<String,Object> map){
        return userServices.getStuCardsInfo(map);
    }

    @RequestMapping(value = "/deleteStuCards")
    @ResponseBody
    public String deleteStuCards(@RequestParam Map<String,Object> map){
        return userServices.deleteStuCards(map);
    }

    @RequestMapping(value = "/insertStuCards")
    @ResponseBody
    public String insertStuCards(@RequestParam Map<String,Object> map){
        return officeServices.insertStuCards(map);
    }

    @RequestMapping(value = "/queryProgressToOffice")
    @ResponseBody
    public Float queryProgressToOffice(@RequestParam Map<String,Object> map){
        return scoreServices.queryProgressToOffice(map);
    }

}
