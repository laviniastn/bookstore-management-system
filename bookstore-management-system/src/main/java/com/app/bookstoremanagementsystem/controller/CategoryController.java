package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Category;
import com.app.bookstoremanagementsystem.service.CategoryService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private static final Logger logger = Logger.getLogger(AuthorController.class);

    @Autowired
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        logger.setLevel(Level.DEBUG);
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        logger.debug("Get All Categories");
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) throws Exception {
        logger.info("Get Category By Id");
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        logger.info("Create Category");
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Category>  updateCategory(@RequestBody Category category) throws Exception {
        logger.info("Update Category");
        return ResponseEntity.ok().body(categoryService.update(category));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        logger.info("Delete Book");
        categoryService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
