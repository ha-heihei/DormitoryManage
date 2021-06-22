package com.lh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiHao
 * @create 20201115 10:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    private String dormitory_id;
    private String date;
    private String score;
    private String student_cards_id;
    private String remark;
    private String imgPath;

}
