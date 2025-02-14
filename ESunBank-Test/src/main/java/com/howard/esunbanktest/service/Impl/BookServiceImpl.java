package com.howard.esunbanktest.service.Impl;

import com.howard.esunbanktest.dao.BookRepository;
import com.howard.esunbanktest.dto.Book;
import com.howard.esunbanktest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {

        List<Map<String, Object>> result = bookRepository.getBooks();

        List<Book> bookList = new ArrayList<Book>();
        for(Map<String, Object> map : result) {

            Book book = new Book();

            book.setName( (String) map.get("name") );
            book.setIsbn( (String) map.get("isbn") );
            book.setAuthor( (String) map.get("author") );
            book.setIntroduction( (String) map.get("introduction") );
            book.setStatus( (Integer) map.get("status") );

            bookList.add(book);
        }

        return bookList;

    } // getBooks end

    @Override
    public Timestamp borrowBook(Integer user_id, String isbn) {

        Map<String, Object> result = bookRepository.borrowBook(user_id, isbn);
        return ( Timestamp ) result.get("borrowing_time_out");

    } // borrowBook end

} // BookServiceImpl end
