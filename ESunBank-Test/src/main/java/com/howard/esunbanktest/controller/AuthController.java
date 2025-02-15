package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.dto.LoginRequest;
import com.howard.esunbanktest.dto.LoginResponse;
import com.howard.esunbanktest.model.User;
import com.howard.esunbanktest.service.LoanService;
import com.howard.esunbanktest.service.UserService;
import com.howard.esunbanktest.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${auth.unauthorized.message}")
    private String UNAUTHORIZED;

    @Value("${jwt.expirationTime}")
    private String TOKEN_EXPIRATION;

    @Value("${jwt.cookie.domain}")
    private String JWT_COOKIE_DOMAIN;

    @Value("${jwt.cookie.secure}")
    private boolean JWT_COOKIE_SECURE;

    @Value("${jwt.cookie.name}")
    private String JWT_COOKIE_NAME;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request,
                                   HttpServletResponse response) {

        boolean isValid = userService.validateUser(request.getPhoneNumber(), request.getPassword());
        if ( !isValid ) { // 帳密錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED);
        }

        // 帳密正確，產生 token
        User user = userService.findUserByPhoneNumber(request.getPhoneNumber());
        String token = jwtUtil.generateToken(request.getPhoneNumber(), user.getUser_id());

        Cookie jwtCookie = new Cookie( JWT_COOKIE_NAME, token );
        jwtCookie.setHttpOnly( true );
        jwtCookie.setSecure( JWT_COOKIE_SECURE );
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge( Integer.parseInt(TOKEN_EXPIRATION) );
        jwtCookie.setDomain( JWT_COOKIE_DOMAIN );

        response.addCookie(jwtCookie);

        return ResponseEntity.ok("login success");

    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {

        String token = jwtUtil.extractTokenFromRequest(request);
        if ( token == null || !jwtUtil.validateToken(token) ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UNAUTHORIZED);
        }

        String phoneNumber = jwtUtil.getSubjectFromToken(token);
        User user = userService.findUserByPhoneNumber(phoneNumber);
        user.setLoanedBooks( loanService.getBorrowedBooks( user.getUser_id() ) );
        return ResponseEntity.ok(user);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie( JWT_COOKIE_NAME, "" );
        jwtCookie.setHttpOnly( true );
        jwtCookie.setSecure( JWT_COOKIE_SECURE );
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge( 0 ); // 立即刪除

        response.addCookie(jwtCookie);
        return ResponseEntity.ok("登出成功");
    }

}
