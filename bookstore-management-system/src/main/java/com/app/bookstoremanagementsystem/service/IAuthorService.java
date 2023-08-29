package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Author;

import java.util.List;

public interface IAuthorService {

    List<Author> getAllAuthors();

    Author getById(Long id) throws Exception;

    Author create(Author author);

    Author update(Author author) throws Exception;

    void deleteById(Long id);
}
