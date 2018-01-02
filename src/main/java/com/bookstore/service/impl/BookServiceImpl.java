package com.bookstore.service.impl;

import com.bookstore.dao.BookCustomMapper;
import com.bookstore.dao.BookMapper;
import com.bookstore.pojo.po.Book;
import com.bookstore.pojo.vo.CountBook;
import com.bookstore.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookCustomMapper bookCustomMapper;
    @Override
    public List<Book> listBooks() {
        List<Book> list = null;
        try {
            list = bookMapper.selectByExample(null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public CountBook countBooks() {
        CountBook book = null;
        try {
            book = bookCustomMapper.countBooks();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return book;
    }
}
