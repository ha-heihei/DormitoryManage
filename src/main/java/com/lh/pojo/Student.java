package com.lh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiHao
 * @create 20201115 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String stunumber;
    private String name;
    private String class_name;
    private String dormitory_id;
    private String remark;
    private String instructor_id;

    private User user;

}
