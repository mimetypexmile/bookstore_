package com.bookstore.service;

import com.bookstore.pojo.po.Admin;

import java.util.List;

public interface AdminService {
    Admin getAdminById(Long id);
    List<Admin> listAdmins();
}
