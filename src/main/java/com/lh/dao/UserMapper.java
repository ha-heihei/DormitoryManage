package com.lh.dao;

import com.lh.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201115 15:14
 */
public interface UserMapper {

    public User checkLogin(Map<String,String> map);

    public User getUserInfo(Integer id);

    public List<Map<String,Object>> getStuCardsList(Map<String,Object> map);

    public Map<String,Object> getStuCardsInfo(Map<String,Object> map);

    public int deleteStuCards(Map<String,Object> map);

    public int getStuCardsListSize();

    public int insertStuCards(Map<String,Object> map);
}
