package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBooks();

    Book getById(Long id) throws Exception;

    Book create(Book book);

    Book update(Book book) throws Exception;


    void deleteById(Long id);
}
