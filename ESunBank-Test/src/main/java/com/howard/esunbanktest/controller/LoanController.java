package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.service.Impl.LoanServiceImpl;
import com.howard.esunbanktest.service.LoanService;
import com.howard.esunbanktest.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class LoanController {

    @Autowired
    private LoanService loanServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/books/{isbn}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable @NotNull String isbn,
                                        HttpServletRequest request) {
        // 取出 JWT
        String token = jwtUtil.extractTokenFromRequest(request);
        // 取得 userid
        Integer userid = jwtUtil.extractUserId(token);

        if ( userid == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請登入後再借書");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(loanServiceImpl.borrowBook(userid, isbn));
    }

    @PutMapping("/books/{isbn}/return")
    public ResponseEntity<?> returnBook(@PathVariable @NotNull String isbn,
                                        HttpServletRequest request) {

        // 取出 JWT
        String token = jwtUtil.extractTokenFromRequest(request);
        // 取得 userid
        Integer userid = jwtUtil.extractUserId(token);

        if ( userid == null ) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("請重新登入");
        }

        return ResponseEntity.status(HttpStatus.OK).body(loanServiceImpl.returnBook(userid, isbn));
    }

} // LoanController end
