package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.service.Impl.LoanServiceImpl;
import com.howard.esunbanktest.service.LoanService;
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

    @PostMapping("/users/{userid}/books/{isbn}/borrow")
    public ResponseEntity<Timestamp> borrowBook(@PathVariable @NotNull Integer userid,
                                                @PathVariable @NotNull String isbn) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loanServiceImpl.borrowBook(userid, isbn));
    }

    @PutMapping("/users/{userid}/books/{isbn}/return")
    public ResponseEntity<Timestamp> returnBook(@PathVariable @NotNull Integer userid,
                                                @PathVariable @NotNull String isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(loanServiceImpl.returnBook(userid, isbn));
    }

} // LoanController end
