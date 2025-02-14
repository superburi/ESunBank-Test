package com.howard.esunbanktest.service.Impl;

import com.howard.esunbanktest.dao.Impl.UserRepositoryImpl;
import com.howard.esunbanktest.dto.RegisterResponse;
import com.howard.esunbanktest.dto.RegisterUser;
import com.howard.esunbanktest.service.UserService;
import com.howard.esunbanktest.util.RegisterUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public RegisterResponse registerUser(RegisterUser User) {
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

} // UserServiceImpl end
