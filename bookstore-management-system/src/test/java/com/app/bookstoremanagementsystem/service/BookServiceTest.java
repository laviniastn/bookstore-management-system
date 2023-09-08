package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.repository.IBookRepository;
import com.app.bookstoremanagementsystem.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class BookServiceTest {

    @MockBean
    private IBookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    public void authorService_getAllBooks() {
        /**
         *  GIVEN
         */

        given(bookRepository.findAll()).willReturn(Utils.getAllBooks());

        /**
         *  WHEN
         */

        List<Book> actualBooks = bookService.getAllBooks();

        /**
         * THEN
         */
        assertEquals(Utils.getAllBooks().size(), actualBooks.size());
        assertEquals(Utils.getAllBooks().get(0).getTitle(), actualBooks.get(0).getTitle());

    }

    @Test
    public void bookService_getBook() throws Exception {
        /**
         *  GIVEN
         */
        given(bookRepository.findById(any())).willReturn(Optional.of(Utils.getBook()));

        /**
         *  WHEN
         */
        Book actualBook = bookService.getById((long) 1);

        /**
         * THEN
         */
        assertEquals(Utils.getBook().getTitle(), actualBook.getTitle());

    }

    @Test
    public void bookService_create() {
        /**
         *  GIVEN
         */

        given(bookRepository.save(any())).willReturn(Utils.getBook());
        long expectedId = Utils.getBook().getId();

        /**
         *  WHEN
         */

        long actualId = bookService.create(Utils.getBook()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }

    @Test
    public void bookService_update() throws Exception {
        /**
         *  GIVEN
         */

        given(bookRepository.findById(any())).willReturn(Optional.ofNullable(Utils.getUpdatedBook()));
        given(bookRepository.save(any())).willReturn(Utils.getUpdatedBook());
        long expectedId = Utils.getUpdatedBook().getId();

        /**
         *  WHEN
         */

        long actualId = bookService.update(Utils.getUpdatedBook()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }


    @Test
    public void bookService_delete() {
        /**
         *  GIVEN
         */

        int expectedSize = 0;
        Mockito.doNothing().when(bookRepository).deleteById(any());

        /**
         *  WHEN
         */
        bookService.deleteById(Utils.getBook().getId());

        /**
         * THEN
         */
        assertEquals(expectedSize, 0);
    }

}
