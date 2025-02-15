package com.howard.esunbanktest.dao;

import com.howard.esunbanktest.model.User;

public interface UserRepository {

    /**
     * 註冊使用者
     * @param phone_number 手機號碼
     * @param password 使用者密碼
     * @param user_name 使用者名稱
     * @return 註冊結果，-1 表示已註冊過 ( 手機號碼已存在 )，1 表示註冊成功 ( insert 成功 )
     */
    public int registerUser(String phone_number, String password, String user_name);

    public User findUserByPhoneNumber(String phone_number);

}
