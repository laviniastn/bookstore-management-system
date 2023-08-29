package com.app.bookstoremanagementsystem.repository;

import com.app.bookstoremanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
