package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.repository.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService{

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author author) throws Exception {
        Optional<Author> authorRepo = authorRepository.findById(author.getId());
        if (!authorRepo.isPresent()) {
            throw new Exception("Author not found with id: "+ author.getId());
        }
        return authorRepository.save(author);
    }

    @Override
    public Author getById(Long id) throws Exception {
        Author author = null;

        if(Objects.nonNull(id)) {
            Optional<Author> optionalAuthor= authorRepository.findById(id);

            if(optionalAuthor.isPresent()){
                author = optionalAuthor.get();
            }else{
                throw new Exception("Author not found with the id: "+id);
            }

        }

        return author;
    }

    @Override
    public void deleteById(Long id) {
        if (Objects.nonNull(id)) {
            authorRepository.deleteById(id);
        }
    }
}
