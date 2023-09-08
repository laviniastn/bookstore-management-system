package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.model.Category;
import com.app.bookstoremanagementsystem.repository.ICategoryRepository;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {

    @MockBean
    private ICategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Test
    public void categoryService_getAllCategories() {
        /**
         *  GIVEN
         */

        given(categoryRepository.findAll()).willReturn(Utils.getAllCategories());

        /**
         *  WHEN
         */

        List<Category> actualCategories = categoryService.getAllCategories();

        /**
         * THEN
         */
        assertEquals(Utils.getAllCategories().size(), actualCategories.size());
        assertEquals(Utils.getAllCategories().get(0).getType(), actualCategories.get(0).getType());

    }

    @Test
    public void categoryService_getCategory() throws Exception {
        /**
         *  GIVEN
         */
        given(categoryRepository.findById(any())).willReturn(Optional.of(Utils.getCategory()));

        /**
         *  WHEN
         */
        Category actualCategory = categoryService.getById((long) 1);

        /**
         * THEN
         */
        assertEquals(Utils.getCategory().getType(), actualCategory.getType());

    }

    @Test
    public void categoryService_create() {
        /**
         *  GIVEN
         */

        given(categoryRepository.save(any())).willReturn(Utils.getCategory());
        long expectedId = Utils.getCategory().getId();

        /**
         *  WHEN
         */

        long actualId = categoryService.create(Utils.getCategory()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }

    @Test
    public void categoryService_update() throws Exception {
        /**
         *  GIVEN
         */

        given(categoryRepository.findById(any())).willReturn(Optional.ofNullable(Utils.getUpdatedCategory()));
        given(categoryRepository.save(any())).willReturn(Utils.getUpdatedCategory());
        long expectedId = Utils.getUpdatedCategory().getId();

        /**
         *  WHEN
         */

        long actualId = categoryService.update(Utils.getUpdatedCategory()).getId();

        /**
         * THEN
         */

        assertEquals(expectedId, actualId);
    }


    @Test
    public void categoryService_delete() {
        /**
         *  GIVEN
         */

        int expectedSize = 0;
        Mockito.doNothing().when(categoryRepository).deleteById(any());

        /**
         *  WHEN
         */
        categoryService.deleteById(Utils.getCategory().getId());

        /**
         * THEN
         */
        assertEquals(expectedSize, 0);
    }

}
