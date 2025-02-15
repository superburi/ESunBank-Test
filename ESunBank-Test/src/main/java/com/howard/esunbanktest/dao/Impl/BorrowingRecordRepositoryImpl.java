package com.howard.esunbanktest.dao.Impl;

import com.howard.esunbanktest.dao.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BorrowingRecordRepositoryImpl implements BorrowingRecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> addBorrowingRecord(Integer user_id, String isbn) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("addBorrowingRecord");

        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("user_id_in", user_id);
        inParams.put("isbn_in", isbn);

        return simpleJdbcCall.execute(inParams);

    } // addBorrowingRecord end

    @Override
    public Map<String, Object> updateBorrowingRecord(Integer user_id, String isbn) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateBorrowingRecord");

        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("user_id_in", user_id);
        inParams.put("isbn_in", isbn);

        return simpleJdbcCall.execute(inParams);

    } // addBorrowingRecord end


} // borrowingRecordRepositoryImpl end
