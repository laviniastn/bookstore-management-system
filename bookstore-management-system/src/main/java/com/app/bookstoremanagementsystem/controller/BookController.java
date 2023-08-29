package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(bookService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Book>  updateAuthor(@RequestBody Book book) throws Exception {
        return ResponseEntity.ok().body(bookService.update(book));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

}
