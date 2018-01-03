package com.bookstore.service;

import com.bookstore.pojo.vo.PersonalOrderVO;

public interface OrderService {

    PersonalOrderVO listUserOrders(Long uid);
}
