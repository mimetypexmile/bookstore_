package com.bookstore.dao;

import com.bookstore.pojo.vo.OrdersVO;

import java.util.List;

public interface OrdersCustomMapper {

    List<OrdersVO> listUserOrders(Integer uid);
}
