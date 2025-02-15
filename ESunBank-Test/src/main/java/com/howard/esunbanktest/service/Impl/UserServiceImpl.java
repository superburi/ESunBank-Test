package com.howard.esunbanktest.service.Impl;

import com.howard.esunbanktest.dao.Impl.UserRepositoryImpl;
import com.howard.esunbanktest.dao.UserRepository;
import com.howard.esunbanktest.dto.RegisterResponse;
import com.howard.esunbanktest.dto.RegisterUser;
import com.howard.esunbanktest.model.User;
import com.howard.esunbanktest.service.UserService;
import com.howard.esunbanktest.constant.RegisterUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterResponse     registerUser(RegisterUser User) {
        // 用來加鹽並 hash 密碼
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        switch (userRepository.registerUser(User.getPhone_number(),
                                            passwordEncoder.encode(User.getPassword()),
                                            User.getUser_name())) {

            case 1  :
                return new RegisterResponse(RegisterUserResult.Success.getReturnCode(),
                                            RegisterUserResult.Success.getReturnMsg());
            case -1 :
                return new RegisterResponse(RegisterUserResult.Failed.getReturnCode(),
                                            RegisterUserResult.Failed.getReturnMsg());
            default :
                return new RegisterResponse(RegisterUserResult.Default.getReturnCode(),
                                            RegisterUserResult.Default.getReturnMsg());

        }

    } // registerUser end

    @Override
    public boolean validateUser( String phoneNumber, String password ) {

        User user = userRepository.findUserByPhoneNumber(phoneNumber);
        if (user == null) { //  沒找到使用者的話直接驗證失敗
            return false;
        }
        return new BCryptPasswordEncoder().matches(password, user.getPassword());

    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber);
    }

} // UserServiceImpl end
