package com.lh.services.impl;

import com.google.gson.*;
import com.lh.dao.StudentMapper;
import com.lh.pojo.Student;
import com.lh.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 12:23
 */
public class StudentServicesImpl implements StudentServices {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private Gson gson;

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public JsonObject getStudentPageList(int start, int end) {
        JsonObject object=new JsonObject();
        object.addProperty("num",studentMapper.getStudentCount());
        List<Student> list=studentMapper.getStudentPageList(start,end);
        object.addProperty("data",gson.toJson(list));
        return object;
    }

    public int getStudentCount() {
        return studentMapper.getStudentCount();
    }

    public JsonObject getStudentListByAll(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("num",studentMapper.getStudentListByAllCount(map));
        object.addProperty("data",gson.toJson(studentMapper.getStudentListByAll(map)));
        return object;
    }

    public Student getStudentInfo(String id) {
        return studentMapper.getStudentInfo(id);
    }

    public String updateStudent(Map<String,String> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        if(studentMapper.updateStudent(map)>0){
            object.addProperty("code",0);
        }
        return gson.toJson(object);
    }
}
