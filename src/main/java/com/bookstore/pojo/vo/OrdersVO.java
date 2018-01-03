package com.bookstore.pojo.vo;

import com.bookstore.pojo.po.Orders;

public class OrdersVO extends Orders{
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
