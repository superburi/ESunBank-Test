package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.dto.Book;
import com.howard.esunbanktest.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

}
