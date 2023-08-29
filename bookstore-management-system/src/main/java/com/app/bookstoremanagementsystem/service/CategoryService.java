package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Category;
import com.app.bookstoremanagementsystem.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) throws Exception {
        Optional<Category> categoryRepo = categoryRepository.findById(category.getId());
        if (!categoryRepo.isPresent()) {
            throw new Exception("Category not found with id: "+ category.getId());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) throws Exception {
        Category category = null;

        if(Objects.nonNull(id)) {
            Optional<Category> optionalCategory= categoryRepository.findById(id);

            if(optionalCategory.isPresent()){
                category = optionalCategory.get();
            }else{
                throw new Exception("Category not found with the id: "+id);
            }
        }
        return category;
    }

    @Override
    public void deleteById(Long id) {
        if(Objects.nonNull(id)){
            this.categoryRepository.deleteById(id);
        }
    }
}
