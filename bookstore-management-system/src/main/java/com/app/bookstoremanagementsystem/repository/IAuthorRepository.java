package com.app.bookstoremanagementsystem.repository;

import com.app.bookstoremanagementsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {

}
