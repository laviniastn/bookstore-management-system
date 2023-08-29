package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();

    Category getById(Long id) throws Exception;

    Category create(Category category);

    Category update(Category category) throws Exception;

    void deleteById(Long id);
}
