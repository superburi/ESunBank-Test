package com.howard.esunbanktest.service.Impl;

import com.howard.esunbanktest.dao.Impl.UserRepositoryImpl;
import com.howard.esunbanktest.dto.RegisterUser;
import com.howard.esunbanktest.service.UserService;
import com.howard.esunbanktest.util.RegisterUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public String registerUser(RegisterUser User) {

        return switch (userRepository.registerUser(User.getPhone_number(),
                User.getPassword(),
                User.getUser_name())) {
            case -1 -> RegisterUserResult.Failed.getReturnMsg();
            case 1  -> RegisterUserResult.Success.getReturnMsg();
            default -> RegisterUserResult.Default.getReturnMsg();
        };

    }

}
