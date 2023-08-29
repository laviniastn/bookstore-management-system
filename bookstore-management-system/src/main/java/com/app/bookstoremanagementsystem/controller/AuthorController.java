package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok().body(authorService.getAllAuthors());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(authorService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author){
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(author));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Author>  updateAuthor(@RequestBody Author author) throws Exception {
        return ResponseEntity.ok().body(authorService.update(author));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteAuthor(@PathVariable("id") Long id) {
        authorService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
