package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.dto.LoginRequest;
import com.howard.esunbanktest.dto.LoginResponse;
import com.howard.esunbanktest.model.User;
import com.howard.esunbanktest.service.UserService;
import com.howard.esunbanktest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${auth.unauthorized.message}")
    private String UNAUTHORIZED;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        boolean isValid = userService.validateUser(request.getPhoneNumber(), request.getPassword());
        if ( !isValid ) { // 帳密錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED);
        }

        // 帳密正確，產生 token
        User user = userService.findUserByPhoneNumber(request.getPhoneNumber());
        String token = jwtUtil.generateToken(request.getPhoneNumber(), user.getUser_id());
        return ResponseEntity.ok().body(new LoginResponse(token));

    }



}
