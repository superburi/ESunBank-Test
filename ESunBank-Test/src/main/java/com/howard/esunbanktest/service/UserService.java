package com.howard.esunbanktest.service;

import com.howard.esunbanktest.dto.RegisterResponse;
import com.howard.esunbanktest.dto.RegisterUser;
import com.howard.esunbanktest.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    /**
     * 註冊使用者
     * @param User 註冊使用者所需資料
     * @return 註冊結果訊息
     */
    public RegisterResponse registerUser(RegisterUser User);

    public boolean validateUser( String phoneNumber, String password );

    public User findUserByPhoneNumber(String phoneNumber);

}
