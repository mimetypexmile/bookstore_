package com.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     *  访问bookstore后台的控制器
     * @return
     */
    @RequestMapping("/index")
    public String toBackgroudIndex(){

        return "index";
    }

    /**
     * 访问登陆页面的控制器
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLoginPage(){
        return "signin";
    }

    /**
     * 新增一个管理员页面的控制器
     * @return
     */
    @RequestMapping("/toSignup")
    public String toSignupPage(){
        return "signup";
    }

    @RequestMapping("/user_order_timeline")
    public String toOrders(){
        return "user_order_timeline";
    }
}
