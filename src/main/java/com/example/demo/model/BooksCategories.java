package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books_categories")
public class BooksCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books book;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    public BooksCategories() {}

    public BooksCategories(Books book, Categories category) {
        this.book = book;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
