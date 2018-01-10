package com.bookstore.service.impl;

import com.bookstore.common.util.MessageResult;
import com.bookstore.dao.UserMapper;
import com.bookstore.pojo.po.Comment;
import com.bookstore.pojo.po.User;
import com.bookstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

    @Override
    public int registerUser(User user) {
        return 0;
    }

    @Override
    public MessageResult loginUser(User user) {
        return null;
    }

    @Override
    public MessageResult banUser(List<Long> ids) {
        return null;
    }

    @Override
    public MessageResult unbanUser(List<Long> ids) {
        return null;
    }

    @Override
    public MessageResult updateUser(User user) {
        return null;
    }

    @Override
    public MessageResult applyForVIP(List<Long> ids) {
        return null;
    }

    @Override
    public MessageResult unapplyVIP(List<Long> ids) {
        return null;
    }

    @Override
    public List<User> listUsers() {
        return null;
    }

    @Override
    public List<User> listUsersBySearch(Map<String, Object> map) {
        return null;
    }

    @Override
    public MessageResult clearIntegration() {
        return null;
    }

    @Override
    public MessageResult totalIntegrationByComment(Comment comment) {
        return null;
    }
}
