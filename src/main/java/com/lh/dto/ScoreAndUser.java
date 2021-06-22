package com.lh.dto;

import com.lh.pojo.Score;
import com.lh.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiHao
 * @create 20201116 21:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreAndUser {

    private User user;

    private Score score;

    private String academy;


}
