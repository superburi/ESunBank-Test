package com.howard.esunbanktest.service;

import com.howard.esunbanktest.dto.Book;

import java.sql.Timestamp;
import java.util.List;

public interface BookService {

    /**
     * 取得所有書籍資料
     * @return 裝著所有書籍資料的 List
     */
    public List<Book> getBooks();

    public Book getBookById(String isbn);

}
