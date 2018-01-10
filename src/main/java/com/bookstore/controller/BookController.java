package com.bookstore.controller;

import com.bookstore.common.util.MessageResult;
import com.bookstore.pojo.po.Admin;
import com.bookstore.pojo.po.Book;
import com.bookstore.pojo.vo.BookVO;
import com.bookstore.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class BookController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BookService bookService;
    //查询所有的图书  默认按照书名的模糊查询
    @RequestMapping(value = "/listBooks")
    public String listAdmins(Model model , @RequestParam(value = "pn",defaultValue ="1")Integer pn, @RequestParam(defaultValue = "") String query, HttpSession session) {
        String encodeQuery = null;
        try {
            encodeQuery = java.net.URLDecoder.decode(query, "UTF-8");
            System.out.println(encodeQuery);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pn,4);
        List<Book> bookList = bookService.listBooks(query);
        PageInfo<Book> pageInfo = new PageInfo(bookList);
        session.setAttribute("query", encodeQuery);
        model.addAttribute("pageInfo",pageInfo);
        return "book_list";
    }

   // 上架
   @RequestMapping("/upBook/{ids}")
   @ResponseBody
   public MessageResult upBook(@PathVariable List<Long> ids){

       MessageResult mr = null;
       try {
           mr = bookService.upBook(ids);
       }catch (Exception e){
           logger.error(e.getMessage(),e);
       }
       return mr;
   }
    @RequestMapping("/downBook/{ids}")
    @ResponseBody
    public MessageResult downBook(@PathVariable List<Long> ids){

        MessageResult mr = null;
        try {
            mr = bookService.downBook(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }

    //
    @RequestMapping("/addInventory/{ids}/{inventory}")
    @ResponseBody
    public MessageResult addBookInventory(@PathVariable List<Long> ids,@PathVariable int inventory){

        MessageResult mr = null;
        try {
            mr = bookService.addBookInventory(ids,inventory);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }
    @RequestMapping("/subInventory/{ids}/{inventory}")
    @ResponseBody
    public MessageResult subBookInventory(@PathVariable List<Long> ids,@PathVariable int inventory){

        MessageResult mr = null;
        try {
            mr = bookService.subBookInventory(ids,inventory);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return mr;
    }

    @ResponseBody
    @RequestMapping("/bookById")
    public BookVO getBookById(Long id){
        BookVO bookVO = bookService.getBookForBack(id);
        return bookVO;
    }

}
