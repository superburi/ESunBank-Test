package com.howard.esunbanktest.service;

import java.sql.Timestamp;

public interface LoanService {

    /**
     * 借書
     * @param user_id 要借書的使用者的 ID
     * @param isbn 該本書的國際書碼
     * @return 借書的時間
     */
    public Timestamp borrowBook(Integer user_id, String isbn);

    /**
     * 還書
     * @param user_id 要還書的使用者的 ID
     * @param isbn 該本書的國際書碼
     * @return 還書的時間
     */
    public Timestamp returnBook(Integer user_id, String isbn);

}
