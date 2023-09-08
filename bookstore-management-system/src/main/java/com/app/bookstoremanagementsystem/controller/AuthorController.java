package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.service.AuthorService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    private static final Logger logger = Logger.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        logger.setLevel(Level.DEBUG);
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors(){
        logger.debug("Get All Authors");
        return ResponseEntity.ok().body(authorService.getAllAuthors());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) throws Exception {
        logger.info("Get Author By Id");
        return ResponseEntity.ok().body(authorService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        logger.info("Create Author");
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(author));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Author>  updateAuthor(@RequestBody Author author) throws Exception {
        logger.info("Update Author");
        return ResponseEntity.ok().body(authorService.update(author));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        logger.info("Delete Author");
        authorService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
