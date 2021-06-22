package com.lh.services;

import com.google.gson.JsonObject;
import com.lh.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 12:22
 */
public interface StudentServices {

    public JsonObject getStudentPageList(int start, int end);

    public int getStudentCount();

    public JsonObject getStudentListByAll(Map<String,Object> map);

    public Student getStudentInfo(String id);

    public String updateStudent(Map<String,String> map);

}
