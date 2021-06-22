package com.lh.services.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.dao.UserMapper;
import com.lh.pojo.User;
import com.lh.services.UserServices;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author LiHao
 * @create 20201115 15:20
 */
public class UserServicesImpl implements UserServices {

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Gson gson;


    @Override
    public JsonObject checkLogin(Map<String, String> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        User user=userMapper.checkLogin(map);
        if(user!=null){
            object.addProperty("code",0);
            object.addProperty("userid",user.getId());
            object.addProperty("status",user.getStatus());
        }
        return object;
    }

    @Override
    public String getUserInfo(Integer id) {
        return gson.toJson(userMapper.getUserInfo(id));
    }

    @Override
    public String getStuCardsList(Map<String,Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("data",gson.toJson(userMapper.getStuCardsList(map)));
        object.addProperty("num",userMapper.getStuCardsListSize());
        return object.toString();
    }

    @Override
    public String getStuCardsInfo(Map<String, Object> map) {
        return gson.toJson(userMapper.getStuCardsInfo(map));
    }

    @Override
    public String deleteStuCards(Map<String, Object> map) {
        JsonObject object=new JsonObject();
        object.addProperty("code",1);
        if(userMapper.deleteStuCards(map)>0){
            object.addProperty("code",0);
        }
        return object.toString();
    }
}
