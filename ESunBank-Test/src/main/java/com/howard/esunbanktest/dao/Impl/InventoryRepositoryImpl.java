package com.howard.esunbanktest.dao.Impl;

import com.howard.esunbanktest.dao.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> updateStatus(String isbn, Integer status) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("updateStatus");

        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("isbn_in", isbn);
        inParams.put("status_in", status);

        return simpleJdbcCall.execute(inParams);

    }

}
