package com.lh.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lh.pojo.User;
import com.lh.services.UserServices;
import com.lh.utils.CheckCode;
import com.lh.utils.CookieUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiHao
 * @create 20201115 11:13
 */
@Controller
public class LoginController {

    @Autowired
    private Gson gson;

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password")String password
            , @RequestParam("usercode")String usercode, HttpServletRequest request,HttpServletResponse response){
        JsonObject object;
        Map<String,String> map=new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        object=userServices.checkLogin(map);
        System.out.println(object);
        if(!usercode.equalsIgnoreCase((String) request.getSession().getAttribute("code"))){
            object.addProperty("code",2);
        }else {
            if(object.get("code").getAsInt()==0){
                response.addCookie(setCookie("userid",object.get("userid").getAsString(),1));
                response.addCookie(setCookie("status",object.get("status").getAsString(),1));
                response.addCookie(setCookie("username",username,1));
            }
        }
        return gson.toJson(object);
    }

    @RequestMapping(value = "/checkcode")
    @ResponseBody
    public byte[] getCheckCode(HttpServletRequest request){
        CheckCode code=CheckCode.Instance();
        request.getSession().setAttribute("code", code.getString());
        return code.getImage().toByteArray();
    }


    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletResponse response){
        CookieUtils.deleteCookie(response,"userid");
        return "login";
    }

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public String getUserInfo(@RequestParam("id")Integer id){
        return userServices.getUserInfo(id);
    }



    private Cookie setCookie(String name, String val, int days) {
        Cookie cookie=new Cookie(name, val);
        cookie.setMaxAge(days*60*60*24);
        cookie.setPath("/");
        return cookie;
    }



}
