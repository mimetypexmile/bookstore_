package com.bookstore.service;

import com.bookstore.pojo.po.Book;
import com.bookstore.pojo.vo.CountBook;

import java.util.List;

public interface BookService {

    List<Book> listBooks();

    CountBook countBooks();
}
