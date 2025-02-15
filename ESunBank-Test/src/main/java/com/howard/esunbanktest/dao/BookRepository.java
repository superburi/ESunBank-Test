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
     * 用 isbn 找一本書的資料
     * @param isbn 該書的 isbn
     * @return 裝著結果的集合
     */
    public Map<String, Object> getBookByID(String isbn);

}
