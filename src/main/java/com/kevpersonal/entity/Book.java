package com.kevpersonal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books_tab", schema = "library_application")
public class Book {

    public Book() {
        this.id = 0L;
        this.author = null;
        this.title = null;
    }

    public Book(String author, String title) {
        this.id = 1L;
        this.author = author;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false, nullable = false)
    final String author;

    @Column(unique = true, nullable = false)
    final String title;

}
