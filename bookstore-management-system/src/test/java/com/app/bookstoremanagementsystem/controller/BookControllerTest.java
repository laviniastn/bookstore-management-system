package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.service.AuthorService;
import com.app.bookstoremanagementsystem.service.BookService;
import com.app.bookstoremanagementsystem.service.CategoryService;
import com.app.bookstoremanagementsystem.utils.Utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    private static final String url = "/book";

    @Test
    public void test_bookController_getAllBooks() throws Exception {

        /**
         *  GIVEN
         */
        given(bookService.getAllBooks()).willReturn(Utils.getAllBooks());

        /**
         *  WHEN
         */
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(url + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */
        List<Book> result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Book>>(){});

        assertEquals(Utils.getAllBooks().get(0).getTitle(),result.get(0).getTitle());

    }

    @Test
    public void test_bookController_getBookById() throws Exception {
        /**
         *  GIVEN
         */
        given(bookService.getById(any())).willReturn(Utils.getBook());

        /**
         *  WHEN
         */
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(url+"/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */
        Book result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertEquals(Utils.getBook().getTitle(),result.getTitle());
    }

    @Test
    public void test_bookController_createBook() throws Exception {
        /**
         *  GIVEN
         */
        given(bookService.create(any())).willReturn(Utils.getBook());

        /**
         *  WHEN
         */
        MvcResult mvcResult = mvc.perform(post(url+"/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.postJsonBodyBook)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        /**
         * THEN
         */
        Book result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertEquals(Utils.getBook().getTitle(),result.getTitle());
    }

    @Test
    public void test_bookController_updateBook() throws Exception {

        /**
         *  GIVEN
         */
        given(bookService.update(any())).willReturn(Utils.getUpdatedBook());

        /**
         *  WHEN
         */
        MvcResult mvcResult = mvc.perform(put(url+"/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.putJsonBodyBook)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        /**
         * THEN
         */
        Book result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Book.class);

        assertEquals(Utils.getUpdatedBook().getTitle(),result.getTitle());
    }

    @Test
    public void test_bookController_deleteBookById() throws Exception {


        /**
         *  WHEN
         */

        MvcResult mvcResult = mvc.perform(delete(url+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
