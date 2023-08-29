package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.model.Category;
import com.app.bookstoremanagementsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.ok().body(categoryService.getById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Category>  updateCategory(@RequestBody Category category) throws Exception {
        return ResponseEntity.ok().body(categoryService.update(category));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
