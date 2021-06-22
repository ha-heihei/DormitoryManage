package com.lh.controller;

import com.google.gson.Gson;
import com.lh.services.ScoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author LiHao
 * @create 20201117 14:26
 */
@Controller
public class InstructorController {

    @Autowired
    private Gson gson;

    @Autowired
    private ScoreServices scoreServices;

    @RequestMapping(value = "/getScoreChartsByCla")
    @ResponseBody
    public String getScoreChartsByCla(@RequestParam Map<String,Object> map){
        return scoreServices.getScoreChartsByCla(map);
    }

    @RequestMapping(value = "/getScoreListByCla")
    @ResponseBody
    public String getScoreListByCla(@RequestParam Map<String,Object> map){
        System.out.println("map = " + map);
        return scoreServices.getScoreListByCla(map);
    }

    @RequestMapping(value = "/queryProgressToInstructor")
    @ResponseBody
    public Float queryProgressToInstructor(@RequestParam Map<String,Object> map){
        return scoreServices.queryProgressToInstructor(map);
    }
}
