package com.app.bookstoremanagementsystem.utils;

import com.app.bookstoremanagementsystem.model.Author;
import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.model.Category;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String putJsonBodyCategory = "{\n" +
            "        \"id\": 1,\n" +
            "        \"type\": \"LITERATURE\"\n" +
            "    }";

    public static final String postJsonBodyCategory = "{\n" +
            "        \"type\": \"KIDS\"\n" +
            "    }";

    public static final String putJsonBodyAuthor = "{\n" +
            "        \"id\": 1,\n" +
            "        \"name\": \"Mihai Eminescu\",\n" +
            "        \"contemporary\": false\n" +
            "    }";

    public static final String postJsonBodyAuthor = "{\n" +
            "        \"name\": \"Jhon Smith\",\n" +
            "        \"contemporary\": false\n" +
            "    }";

    public static final String putJsonBodyBook = "{\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"Stories for kids\",\n" +
            "        \"description\": \"Bedtime stories for kids\",\n" +
            "        \"price\": 20,\n" +
            "        \"amount\": 10\n" +
            "    }";

    public static final String postJsonBodyBook = "{\n" +
            "        \"title\": \"Stories for kids\",\n" +
            "        \"description\": \"Bedtime stories for kids\",\n" +
            "        \"price\": 25,\n" +
            "        \"amount\": 5\n" +
            "    }";

    public static Category getCategory(){
        return new Category((long)1,"KIDS");
    }
    public static Category getUpdatedCategory(){
        return new Category((long) 1, "LITERATURE");
    }

    public static Book getBook(){
        Book book = new Book((long) 1,"Stories for kids","Bedtime stories",20,10);
        book.setCategory(new Category("KIDS"));
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Jhon Smith", false));
        book.setAuthors(authors);

        return book;
    }

    public static Book getUpdatedBook(){
        Book book = new Book((long) 1,"Stories for kids","Bedtime stories for kids",25,5);
        book.setCategory(new Category("KIDS"));
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Jhon Smith", false));
        book.setAuthors(authors);

        return book;
    }

    public static List<Book> getAllBooks(){
        Book book = new Book("Stories for kids","Bedtime stories for kids",25,5);
        book.setCategory(new Category("KIDS"));
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Jhon Smith", false));
        book.setAuthors(authors);

        List<Book> books = new ArrayList<>();
        books.add(book);

        return books;
    }

    public static List<Author> getAllAuthors(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Jhon Smith", false));

        return authors;
    }

    public static List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("KIDS"));

        return categories;
    }

    public static Author getAuthor(){
        return new Author((long) 1,"Mihai Eminescu",false);
    }

    public static Author getUpdatedAuthor(){
        return new Author((long) 1,"Jhon Smith",false);
    }
}
