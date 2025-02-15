package com.howard.esunbanktest.service.Impl;

import com.howard.esunbanktest.dao.BorrowingRecordRepository;
import com.howard.esunbanktest.dao.InventoryRepository;
import com.howard.esunbanktest.service.LoanService;
import com.howard.esunbanktest.constant.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional
    @Override
    public Timestamp borrowBook(Integer user_id, String isbn) {
        // 狀態改成 已借閱
        inventoryRepository.updateStatus(isbn, BookStatus.Loaned.getStatusCode());
        // 新增借閱紀錄
        Map<String, Object> result = borrowingRecordRepository.addBorrowingRecord(user_id, isbn);
        return ( Timestamp ) result.get("borrowing_time_out");

    } // borrowBook end

    @Transactional
    @Override
    public Timestamp returnBook(Integer user_id, String isbn) {
        // 狀態改成 在庫
        inventoryRepository.updateStatus(isbn, BookStatus.INSTOCK.getStatusCode());
        // 更新歸還時間
        Map<String, Object> result = borrowingRecordRepository.updateBorrowingRecord(user_id, isbn);
        return ( Timestamp ) result.get("return_time");
    }

}
