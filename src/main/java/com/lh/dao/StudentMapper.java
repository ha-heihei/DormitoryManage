package com.lh.dao;

import com.lh.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201116 12:15
 */
public interface StudentMapper {

    public List<Student> getStudentPageList(int start,int end);

    public int getStudentCount();

    public List<Student> getStudentListByAll(Map<String,Object> map);

    public int getStudentListByAllCount(Map<String,Object> map);

    public Student getStudentInfo(String id);

    public int updateStudent(Map<String,String> map);

}
