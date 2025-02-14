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

    /**
     * 借書
     * @param user_id 要借書的使用者的 ID
     * @param isbn 該本書的國際書碼
     * @return 借書的時間
     */
    public Timestamp borrowBook(Integer user_id, String isbn);

}
