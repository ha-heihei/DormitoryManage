package com.lh.dao;

import java.util.List;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201203 19:10
 */
public interface QNMapper {

    public int insertQN(Map<String,Object> map);

    public List<Map<String,Object>> queryQN(Map<String,Object> map);

    public int queryQNCount(Map<String,Object> map);

    public List<Map<String,Object>> downLoadQN(Map<String,Object> map);

}
