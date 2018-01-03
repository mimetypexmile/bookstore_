package com.bookstore.controller;

import com.bookstore.pojo.vo.PersonalOrderVO;
import com.bookstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserOrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;
    @ResponseBody
    @RequestMapping("/data")
    public PersonalOrderVO listUserOrders(Long uid){
        PersonalOrderVO personalOrderVO = null;
        try {
             personalOrderVO = orderService.listUserOrders(uid);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return personalOrderVO;
    }
}
