package com.bookstore.controller;

import com.bookstore.common.util.JsonUtils;
import com.bookstore.common.util.MD5Util;
import com.bookstore.common.util.MessageResult;
import com.bookstore.pojo.po.User;
import com.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @RequestMapping("/saveUser")
    @ResponseBody
    public MessageResult registerUser(User user){
        MessageResult ms = new MessageResult();
        int count = 0;

        try {
            count = userService.registerUser(user);
            if (count > 0) {
                user.setPassword(null);
                ms.setData(JsonUtils.objectToJson(user));
                ms.setSuccess(true);
                ms.setMessage("创建成功");

            } else {
                ms.setData(JsonUtils.objectToJson(user));
                ms.setMessage("创建失败");
                ms.setSuccess(false);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ms;
    }

    //用户登陆
    @ResponseBody
    @RequestMapping(value = "/user/login", produces = "application/json;charset=UTF-8",
            method = RequestMethod.POST)
    public MessageResult login(String name, String password,HttpSession session) {

        System.out.println(name + password);
        MessageResult ms = new MessageResult();
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        User existUser = null;
        try {
            existUser = userService.login(user);
            if (existUser == null) {
                //管理员名称不存在
                ms.setSuccess(false);
                ms.setMessage("该账户不存在");
                ms.setData(user);
                return ms;
            } else {
                //管理员名称存在
                //todo  MD5加密
                if (MD5Util.MD5(user.getPassword()).equals(existUser.getPassword())) {
                    //账户和密码都匹配
                    ms.setSuccess(true);
                    ms.setMessage("登录成功");
                    //登录成功的Admin存入session
                    session.setAttribute("session_User",existUser);
                    existUser.setPassword(null);
                    String json = JsonUtils.objectToJson(existUser);
                    ms.setData(json);
                    return ms;
                } else {
                    //管理员密码输入不正确
                    ms.setMessage("账号和密码不匹配");
                    ms.setSuccess(false);
                    ms.setData(user);
                    return ms;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return ms;
    }

    //ajax 去查询管理员名是否存在
    @RequestMapping(value = "/user/{name}", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    @ResponseBody
    public MessageResult getUserByName(@PathVariable String name) {
        String loginName=null;
        try {
            loginName = java.net.URLDecoder.decode(name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MessageResult mr = new MessageResult();
        try {
            if(loginName ==null){
                mr.setSuccess(false);
                mr.setMessage("未知的错误");
                return mr;
            }
            mr = userService.getUserByName(loginName);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return mr;
    }

    //用户退出到登录界面
    @RequestMapping("/user_logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:toLoginProtal";
    }


}
