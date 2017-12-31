package com.bookstore.controller;

import com.bookstore.pojo.po.Admin;
import com.bookstore.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdminService adminService;
    @ResponseBody
    @RequestMapping("/admin")
    public Admin getAdminById(Long id){

        Admin admin = null;
        try {
            admin = adminService.getAdminById(id);
        }catch (Exception e){

            logger.error(e.getMessage(),e);
        }

        return admin;
    }

    //查询所有的管理员
    @RequestMapping(value = "/listAdmins")
    public String listAdmins(Model model , @RequestParam(value = "pn",defaultValue ="1")Integer pn) {

        PageHelper.startPage(pn,4);
        List<Admin> adminList = adminService.listAdmins();
        //todo 泛型需要自己写vo
        PageInfo<Admin> pageInfo = new PageInfo(adminList);

        model.addAttribute("pageInfo",pageInfo);
        return "admin_list";
    }
}
