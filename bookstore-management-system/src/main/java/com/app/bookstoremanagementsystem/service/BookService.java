package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
           return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) throws Exception {
        Optional<Book> bookRepo = bookRepository.findById(book.getId());
        if (!bookRepo.isPresent()) {
            throw new Exception("Book not found with id: "+ book.getId());
        }
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) throws Exception {
        Book book = null;

        if(Objects.nonNull(id)) {
          Optional<Book> optionalBook= bookRepository.findById(id);

          if(optionalBook.isPresent()){
              book = optionalBook.get();
          }else{
              throw new Exception("Book not found with the id: "+id);
          }

        }

        return book;
    }

    @Override
    public void deleteById(Long id) {
        if(Objects.nonNull(id)){
            this.bookRepository.deleteById(id);
        }

    }
}
