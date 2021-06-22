package com.lh.controller;

import com.lh.pojo.Student;
import com.lh.services.QNServices;
import com.lh.services.ScoreServices;
import com.lh.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201203 19:08
 */
@Controller
public class StudentController {

    @Autowired
    private QNServices qnServices;

    @Autowired
    private ScoreServices scoreServices;

    @Autowired
    private StudentServices studentServices;

    @RequestMapping(value = "/insertQN")
    @ResponseBody
    public String insertQN(@RequestParam Map<String,Object> map){
        return qnServices.insertQN(map);
    }

    @RequestMapping(value = "/queryDorScore")
    @ResponseBody
    public String queryDorScore(@RequestParam Map<String,Object> map, HttpServletRequest request){
        String stunumber="";
        for(Cookie cookie:request.getCookies()){
            if(cookie.getName()!=null&&cookie.getName().equals("userid")){
                stunumber=cookie.getValue();
                break;
            }
        }
        Student student=studentServices.getStudentInfo(stunumber);
        map.put("dormitoryId",student.getDormitory_id());
        return scoreServices.queryDorScore(map);
    }

    @RequestMapping(value = "/queryPageDorScore")
    @ResponseBody
    public String queryPageDorScore(@RequestParam Map<String,Object> map){
        map.put("start",Integer.parseInt(map.get("start").toString()));
        map.put("end",Integer.parseInt(map.get("end").toString()));
        return scoreServices.queryPageDorScore(map);
    }
}
