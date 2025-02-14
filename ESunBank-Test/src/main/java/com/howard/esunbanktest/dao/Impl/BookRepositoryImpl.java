package com.howard.esunbanktest.dao.Impl;

import com.howard.esunbanktest.dao.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getBooks() {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("getBooks");

        Map<String, Object> result = simpleJdbcCall.execute();
        List<Map<String, Object>> books = (List<Map<String, Object>>) result.get("#result-set-1");

        return books;

    }

}
