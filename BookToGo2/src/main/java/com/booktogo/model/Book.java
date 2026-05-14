package com.booktogo.model;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private String status;
    private double averageRating;
    private String description;

    public Book(String isbn, String title, String author, String genre, String status, double averageRating, String description) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
        this.averageRating = averageRating;
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getStatus() {
        return status;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getDescription() {
        return description;
    }
}