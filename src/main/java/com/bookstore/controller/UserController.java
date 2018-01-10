package com.bookstore.controller;

import com.bookstore.common.util.JsonUtils;
import com.bookstore.common.util.MessageResult;
import com.bookstore.pojo.po.User;
import com.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
