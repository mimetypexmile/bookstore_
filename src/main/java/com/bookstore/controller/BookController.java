package com.bookstore.controller;

import com.bookstore.pojo.po.Book;
import com.bookstore.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookService bookService;
    @RequestMapping("/listBooks")
    public String listBooks(Model model , @RequestParam(value = "pn",defaultValue ="1")Integer pn){

        PageHelper.startPage(pn,4);
        List<Book> list = bookService.listBooks();
        PageInfo<Book> pageInfo = new PageInfo(list);

        model.addAttribute("pageInfo",pageInfo);
        return "book_list";
    }

}
