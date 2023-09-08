package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.model.Category;
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
public class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BookService bookService;

    private static final String url = "/category";

    @Test
    public void test_categoryController_getAllCategories() throws Exception {

        /**
         *  GIVEN
         */
        given(categoryService.getAllCategories()).willReturn(Utils.getAllCategories());

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
        List<Category> result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Category>>(){});
        assertEquals(Utils.getAllCategories().get(0).getType(),result.get(0).getType());

    }

    @Test
    public void test_categoryController_getCategoryById() throws Exception {

        /**
         * GIVEN
         */
        given(categoryService.getById(any())).willReturn(Utils.getCategory());

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
        Category result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals(Utils.getCategory().getType(),result.getType());
    }

    @Test
    public void test_categoryController_createCategory() throws Exception {

        /**
         * GIVEN
         */
        given(categoryService.create(any())).willReturn(Utils.getCategory());

        /**
         * WHEN
         */
        MvcResult mvcResult = mvc.perform(post(url+"/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.postJsonBodyCategory)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        /**
         * THEN
         */
        Category result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals(Utils.getCategory().getType(),result.getType());


    }

    @Test
    public void test_categoryController_updateCategory() throws Exception {
        /**
         * GIVEN
         */
        given(categoryService.update(any())).willReturn(Utils.getUpdatedCategory());

        /**
         * WHEN
         */

        MvcResult mvcResult = mvc.perform(put(url+"/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.putJsonBodyCategory)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        /**
         * THEN
         */
        Category result = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), Category.class);
        assertEquals(Utils.getUpdatedCategory().getType(),result.getType());


    }

    @Test
    public void test_categoryController_deleteCategoryById() throws Exception {

        MvcResult mvcResult = mvc.perform(delete(url+"/delete/1"))
                .andExpect(status().isOk())
                .andReturn();

    }
}
