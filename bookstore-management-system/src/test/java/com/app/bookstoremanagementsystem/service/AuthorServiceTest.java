package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.repository.IAuthorRepository;
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
public class AuthorServiceTest {

    @MockBean
    private IAuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    @Test
    public void authorService_getAllAuthors() {
        /**
         *  GIVEN
         */

        given(authorRepository.findAll()).willReturn(Utils.getAllAuthors());

        /**
         *  WHEN
         */

        List<Author> actualAuthors = authorService.getAllAuthors();

        /**
         * THEN
         */
        assertEquals(Utils.getAllAuthors().size(), actualAuthors.size());
        assertEquals(Utils.getAllAuthors().get(0).getName(), actualAuthors.get(0).getName());

      }

    @Test
    public void authorService_getAuthor() throws Exception {
        /**
         *  GIVEN
         */
        given(authorRepository.findById(any())).willReturn(Optional.of(Utils.getAuthor()));

        /**
         *  WHEN
         */
        Author actualAuthor = authorService.getById((long) 1);

        /**
         * THEN
         */
        assertEquals(Utils.getAuthor().getName(), actualAuthor.getName());

    }

    @Test
    public void authorService_create() {
        /**
         *  GIVEN
         */

        given(authorRepository.save(any())).willReturn(Utils.getAuthor());
        long expectedId = Utils.getAuthor().getId();

        /**
         *  WHEN
         */

        long actualId = authorService.create(Utils.getAuthor()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }

    @Test
    public void authorService_update() throws Exception {
        /**
         *  GIVEN
         */

        given(authorRepository.findById(any())).willReturn(Optional.ofNullable(Utils.getUpdatedAuthor()));
        given(authorRepository.save(any())).willReturn(Utils.getUpdatedAuthor());
        long expectedId = Utils.getUpdatedAuthor().getId();

        /**
         *  WHEN
         */

        long actualId = authorService.update(Utils.getUpdatedAuthor()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }


    @Test
    public void authorService_delete() {
        /**
         *  GIVEN
         */

        int expectedSize = 0;
        Mockito.doNothing().when(authorRepository).deleteById(any());

        /**
         *  WHEN
         */
        authorService.deleteById(Utils.getAuthor().getId());

        /**
         * THEN
         */
        assertEquals(expectedSize, 0);
    }

}
