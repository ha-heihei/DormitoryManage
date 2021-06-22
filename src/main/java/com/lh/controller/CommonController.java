package com.lh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author PangJiaHeng
 * @create 20201115 16:49
 */
@Controller
public class CommonController {

    @RequestMapping(value = "/forward/{path}")
    public String forward(@PathVariable("path")String path){
        System.out.println("path = " + path);
        return "/"+path;
    }

    @RequestMapping(value = "/redirect/{path}")
    public String redirect(@PathVariable("path")String path){
        return "redirect:"+path;
    }

    @RequestMapping(value = "/stu/score")
    public String toStuScore(){
        return "studentcards/score";
    }

    @RequestMapping(value = "/stu/stulist")
    public String toStuList(){
        return "studentcards/studentlist";
    }

    @RequestMapping(value = "/stu/scorelist")
    public String toScoreList(){
        return "studentcards/scorelist";
    }

    @RequestMapping(value = "/instructor/charts")
    public String toCharts(){
        return "instructor/charts";
    }

    @RequestMapping(value = "/office/index")
    public String toOfficeIndex(){
        return "office/index";
    }

    @RequestMapping(value = "/office/scolist")
    public String toOfficeScoList(){
        return "office/scolist";
    }

    @RequestMapping(value = "/office/stucards")
    public String toOfficeStucards(){
        return "office/stucards";
    }

    @RequestMapping(value = "/toLoginPage")
    public String toReceptionPage(){
        return "login";
    }

    @RequestMapping(value = "/student/questionnaire")
    public String toQuestionNaire(){
        return "student/questionnaire";
    }

    @RequestMapping(value = "/student/queryscore")
    public String toQueryScore(){
        return "student/queryscore";
    }

    @RequestMapping(value = "/stu/templist")
    public String toTempList(){
        return "studentcards/templist";
    }

    @RequestMapping(value = "/office/addstucards")
    public String toAddStuCards(){
        return "office/addstucards";
    }
}
