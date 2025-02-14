package com.howard.esunbanktest.dao.Impl;

import com.howard.esunbanktest.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int registerUser(String phone_number, String password, String user_name) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("registerUser");

        // 準備呼叫 procedure 要帶入的引數
        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("phone_number_in", phone_number);
        inParams.put("password_in", password);
        inParams.put("user_name_in", user_name);

        // 執行 procedure
        Map<String, Object> outParams = simpleJdbcCall.execute(inParams);

        return ( int ) outParams.get("result");

    }

}
