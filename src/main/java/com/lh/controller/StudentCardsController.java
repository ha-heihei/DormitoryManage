package com.lh.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.pojo.Score;
import com.lh.services.QNServices;
import com.lh.services.ScoreServices;
import com.lh.services.StudentServices;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 10:42
 */
@Controller
public class StudentCardsController {

    @Autowired
    private ScoreServices scoreServices;

    @Autowired
    private StudentServices studentServices;

    @Autowired
    private QNServices qnServices;

    @Autowired
    private Gson gson;


    @RequestMapping(value = "/stuscore")
    @ResponseBody
    public String stuScore(Score score, HttpServletRequest request,
                           @RequestParam(value = "img",required = false)MultipartFile file) throws IOException {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        String studentCardsId="";
        String imgPath="";
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName().equalsIgnoreCase("userid")){
                studentCardsId=cookie.getValue();
                break;
            }
        }
        String basePath=request.getServletContext().getRealPath("/res/images/dormitory/");
        if(file!=null){
            imgPath=file.getOriginalFilename();
            System.out.println(basePath+imgPath);
            file.transferTo(new File(basePath+imgPath));
        }
        score.setImgPath("/res/images/dormitory/"+imgPath);
        score.setStudent_cards_id(studentCardsId);
        if(scoreServices.insertScore(score)>0){
            object.addProperty("code",0);
        }
        return gson.toJson(object);
    }

    @RequestMapping(value = "/getStudentPageList")
    @ResponseBody
    public String getStudentPageList(@RequestParam("start")int start,@RequestParam("end")int end){
        return gson.toJson(studentServices.getStudentPageList(start,end));
    }

    @RequestMapping(value = "/getStudentListByAll")
    @ResponseBody
    public String getStudentListByAll(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt((String) map.get("start")));
        map.put("end",Integer.parseInt((String) map.get("end")));
        System.out.println("map = " + gson.toJson(map));
        return gson.toJson(studentServices.getStudentListByAll(map));
    }

    @RequestMapping(value = "/getStudentInfo")
    @ResponseBody
    public String getStudentInfo(@RequestParam("id")String id){
        return gson.toJson(studentServices.getStudentInfo(id));
    }

    @RequestMapping(value = "/updateStudent")
    @ResponseBody
    public String updateStudent(@RequestParam Map<String,String> map){
        return studentServices.updateStudent(map);
    }


    @RequestMapping(value = "/getScoreList")
    @ResponseBody
    public String getScoreList(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt((String) map.get("start")));
        map.put("end",Integer.parseInt((String) map.get("end")));
        return scoreServices.getScoreList(map);
    }

    @RequestMapping(value = "/updateScore")
    @ResponseBody
    public String updateScore(@RequestParam Map<String,Object> map){
        return scoreServices.updateScore(map);
    }


    @RequestMapping(value = "/downLoadExcel")
    @ResponseBody
    public void downLoadExcel(@RequestParam Map<String,Object> map, HttpServletResponse response) throws IOException {
        System.out.println("map = " + map);
        String fileName= new String("查宿信息表.xls".getBytes("UTF-8"),"iso-8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        HSSFWorkbook workbook=scoreServices.downLoadScoreListByDate(map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }


    @RequestMapping(value = "/queryQN")
    @ResponseBody
    public String queryQN(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt(map.get("start").toString()));
        map.put("end",Integer.parseInt(map.get("end").toString()));
        return qnServices.queryQN(map);
    }

    @RequestMapping(value = "/downLoadQN")
    @ResponseBody
    public void downLoadQN(@RequestParam Map<String,Object> map,HttpServletResponse response) throws IOException{
        String fileName= new String("体温表.xls".getBytes("UTF-8"),"iso-8859-1");
        response.setHeader("Content-disposition", "attachment;filename="+fileName);
        HSSFWorkbook workbook=qnServices.downLoadQN(map);
        workbook.write(response.getOutputStream());
        workbook.close();
    }




}
