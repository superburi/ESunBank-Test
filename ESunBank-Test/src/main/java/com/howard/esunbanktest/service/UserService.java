package com.howard.esunbanktest.service;

import com.howard.esunbanktest.dto.RegisterUser;

public interface UserService {

    /**
     *
     * @param User 註冊使用者所需資料
     * @return 註冊結果訊息
     */
    public String registerUser(RegisterUser User);

}
