package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Author;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    private static final String url = "/author";

    @Test
    public void test_authorController_getAllAuthors() throws Exception {

        /**
         *  GIVEN
         */
        given(authorService.getAllAuthors()).willReturn(Utils.getAllAuthors());

        /**
         * WHEN
         */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(url + "/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */
        List<Author> result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Author>>(){});

        assertEquals(Utils.getAllAuthors().get(0).getName(),result.get(0).getName());

    }

    @Test
    public void test_authorController_getAuthorById() throws Exception {

        /**
         *  GIVEN
         */
        given(authorService.getById(any())).willReturn(Utils.getAuthor());

        /**
         * WHEN
         */

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get(url+"/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */
        Author result= new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Author.class);
        assertEquals(Utils.getAuthor().getName(),result.getName());
    }

   @Test
   public void test_authorController_createAuthor() throws Exception {
       /**
        * GIVEN
        */

       given(authorService.create(any())).willReturn(Utils.getAuthor());

       /**
        * WHEN
        */
       MvcResult mvcResult = mvc.perform(post(url+"/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.postJsonBodyAuthor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

       /**
        * THEN
        */

       Author result= new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Author.class);
       assertEquals(Utils.getAuthor().getName(),result.getName());
   }

    @Test
    public void test_authorController_updateAuthor() throws Exception {
        /**
         * GIVEN
         */

        given(authorService.update(any())).willReturn(Utils.getUpdatedAuthor());

        /**
         * WHEN
         */

        MvcResult mvcResult = mvc.perform(put(url+"/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.putJsonBodyAuthor)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */

        Author result= new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Author.class);
        assertEquals(Utils.getUpdatedAuthor().getName(),result.getName());

    }

    @Test
    public void test_authorController_deleteAuthorById() throws Exception {

        MvcResult mvcResult = mvc.perform(delete(url+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
