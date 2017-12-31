package com.bookstore.service;

import com.bookstore.common.util.MessageResult;
import com.bookstore.pojo.po.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminById(Long id);
    List<Admin> listAdmins();
    int saveAdmin(Admin admin);
    MessageResult getAdminByName(String name);
    Admin login(Admin admin);
}
