package com.app.bookstoremanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean contemporary;

    public Author(String name, Boolean contemporary) {
        this.name=name;
        this.contemporary = contemporary;
    }

    public Author(long id, String name, boolean contemporary) {
        this.id=id;
        this.name=name;
        this.contemporary = contemporary;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contemporary=" + contemporary +
                '}';
    }
}
