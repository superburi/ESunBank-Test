package com.howard.esunbanktest.dao;

import java.sql.Timestamp;
import java.util.Map;

public interface BorrowingRecordRepository {

    public Map<String, Object> addBorrowingRecord(Integer user_id, String isbn);

    public Map<String, Object> updateBorrowingRecord(Integer user_id, String isbn);

    public Map<String, Object> getBorrowedBooks(Integer user_id);

}
