package com.bookstore.dao;

import com.bookstore.pojo.vo.BookVO;
import com.bookstore.pojo.vo.CountBook;

import java.util.List;

public interface BookCustomMapper {

    CountBook countBooks();

    BookVO getBookForBack(Long id);
}
