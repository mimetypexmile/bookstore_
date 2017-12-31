package com.bookstore.service.impl;

import com.bookstore.common.util.MD5Util;
import com.bookstore.common.util.MessageResult;
import com.bookstore.dao.AdminMapper;
import com.bookstore.pojo.po.Admin;
import com.bookstore.pojo.po.AdminExample;
import com.bookstore.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public int saveAdmin(Admin admin) {
        // 管理员的id 自增
        //密码MD5 加密
        admin.setPassword(MD5Util.MD5(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        admin.setIsDelete("0");
        admin.setStatus((byte)0);
        int count = 0;
        try {
            count = adminMapper.insertSelective(admin);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return count;
    }

    @Override
    public MessageResult getAdminByName(String name) {
        MessageResult mr = new MessageResult();
        AdminExample example = new AdminExample();
        AdminExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<Admin> adminList = adminMapper.selectByExample(example);
        if(adminList.size()>0){
            mr.setSuccess(false);
            mr.setMessage("该账户已经被注册");
        }else {
            mr.setMessage("账户可使用");
            mr.setSuccess(true);
        }
        return mr;
    }

    @Override
    public Admin login(Admin admin) {
        Admin existAdmin = null;
        try{
            AdminExample example = new AdminExample();
            AdminExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(admin.getName());
            List<Admin> admins = adminMapper.selectByExample(example);
            if(admins.size()>0)
                existAdmin = admins.get(0);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        if(existAdmin != null){
            if(existAdmin.getIsDelete() == "0"){
                //todo 更新数据库的上一次登陆时间
            }
        }
        return existAdmin;
    }
}
