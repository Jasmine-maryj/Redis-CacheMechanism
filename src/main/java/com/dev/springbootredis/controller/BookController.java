package com.dev.springbootredis.controller;

import com.dev.springbootredis.entity.Book;
import com.dev.springbootredis.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/book")
    public ResponseEntity<Book> save(@RequestBody Book book){
        Book book1 = bookRepository.save(book);
        return new ResponseEntity<>(book1, HttpStatus.CREATED);
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> findAll(){
        List<Book> bookList = bookRepository.findAll();
        return ResponseEntity.ok(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable("id") int id){
        Book book = bookRepository.findBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") int id){
        String result = bookRepository.deleteBookById(id);
        return ResponseEntity.ok(result);
    }

}
