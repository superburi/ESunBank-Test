package com.howard.esunbanktest.controller;

import com.howard.esunbanktest.dto.Book;
import com.howard.esunbanktest.service.BookService;
import com.howard.esunbanktest.service.Impl.BookServiceImpl;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBooks());
    }

    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBookById(@PathVariable @NotNull String isbn) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(isbn));
    }

}
