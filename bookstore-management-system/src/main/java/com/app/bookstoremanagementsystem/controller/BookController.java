package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.service.BookService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    private static final Logger logger = Logger.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        logger.setLevel(Level.DEBUG);
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        logger.debug("Get All Books");
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws Exception {
        logger.info("Get Book By Id");
        return ResponseEntity.ok().body(bookService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        logger.info("Create Book");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(book));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Book>  updateBook(@RequestBody Book book) throws Exception {
        logger.info("Update Book");
        return ResponseEntity.ok().body(bookService.update(book));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id) {
        logger.info("Delete Book");
        bookService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

}
