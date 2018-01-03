package com.bookstore.service.impl;

import com.bookstore.dao.OrdersCustomMapper;
import com.bookstore.dao.OrdersMapper;
import com.bookstore.pojo.po.Orders;
import com.bookstore.pojo.po.OrdersExample;
import com.bookstore.pojo.po.UserExample;
import com.bookstore.pojo.vo.OrdersVO;
import com.bookstore.pojo.vo.PersonalOrderVO;
import com.bookstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrdersCustomMapper ordersCustomMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public PersonalOrderVO listUserOrders(Long uid) {
        PersonalOrderVO personalOrderVO = new PersonalOrderVO();
        OrdersExample example = new OrdersExample();
        OrdersExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(uid);
        try {
            List<Orders> orders = ordersMapper.selectByExampleWithBLOBs(example);
            personalOrderVO.setTitle("订单");
            personalOrderVO.setList(orders);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return personalOrderVO;
    }
}
