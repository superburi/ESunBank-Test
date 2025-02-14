package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.dto.RegisterResponse;
import com.howard.esunbanktest.dto.RegisterUser;
import com.howard.esunbanktest.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterUser user) {

        RegisterResponse response = userService.registerUser(user);

        switch (response.getReturnCode()) {

            case 1  :
                return ResponseEntity.status(HttpStatus.CREATED)
                                     .body(response);
            case -1 :
                return ResponseEntity.status(HttpStatus.CONFLICT)
                                     .body(response);
            default :
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                     .body(response);

        }

    } // register end

} // UserController end
