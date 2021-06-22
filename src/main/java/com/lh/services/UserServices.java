package com.lh.services;

import com.google.gson.JsonObject;
import com.lh.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201115 15:20
 */
public interface UserServices {

    public JsonObject checkLogin(Map<String,String> map);

    public String getUserInfo(Integer id);

    public String getStuCardsList(Map<String,Object> map);

    public String getStuCardsInfo(Map<String,Object> map);

    public String deleteStuCards(Map<String,Object> map);

}
