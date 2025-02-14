package com.howard.esunbanktest.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface BookRepository {

    /**
     * 取得所有書籍資料
     * @return 裝著所有書籍資料的 List
     */
    public List<Map<String, Object>> getBooks();

    /**
     * 借書
     * @param user_id 要借書的使用者的 ID
     * @param isbn 該本書的國際書碼
     * @return 裝著借書時間的 Map
     */
    public Map<String, Object> borrowBook(Integer user_id, String isbn);

}
