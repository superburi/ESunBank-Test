package com.howard.esunbanktest.dao.Impl;

import com.howard.esunbanktest.dao.UserRepository;
import com.howard.esunbanktest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public User findUserByPhoneNumber(String phone_number) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("findUserByPhoneNumber");

        Map<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("phone_number_in", phone_number);


        List<Map<String, Object>> results = (List<Map<String, Object>>) simpleJdbcCall.execute(inParams)
                                                                                      .get("#result-set-1");
        Map<String, Object> outParams = results.get(0);
        User user = new User();
        user.setUser_id( ( Integer ) outParams.get("user_id"));
        user.setPhone_number( ( String ) outParams.get("phone_number"));
        user.setPassword( ( String ) outParams.get("password"));
        user.setUser_name( ( String ) outParams.get("user_name"));
        user.setRegistration_time( (LocalDateTime) outParams.get("registration_time"));
        user.setLast_login_time( (LocalDateTime) outParams.get("last_login_time"));
        return user;

    }

}
