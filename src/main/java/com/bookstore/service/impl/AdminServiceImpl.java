package com.bookstore.service.impl;

import com.bookstore.dao.AdminMapper;
import com.bookstore.pojo.po.Admin;
import com.bookstore.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl implements AdminService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public Admin getAdminById(Long id) {
        Admin admin = null;
        try {
            admin = adminMapper.selectByPrimaryKey(id);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return admin;
    }

    @Override
    public List<Admin> listAdmins() {
        List<Admin> list = null;
        try {
            list = adminMapper.selectByExample(null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return list;
    }
}
