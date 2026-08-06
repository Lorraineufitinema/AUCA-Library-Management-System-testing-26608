package com.auca.domain;

import java.util.UUID;

import com.auca.enums.BookStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookId;

    private String title;

    private String isbnCode;

    private int publicationYear;

    private String publisherName;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

    public Book() {
    }

    public Book(UUID bookId, String title, String isbnCode,
            int publicationYear, String publisherName,
            BookStatus status) {

        this.bookId = bookId;
        this.title = title;
        this.isbnCode = isbnCode;
        this.publicationYear = publicationYear;
        this.publisherName = publisherName;
        this.status = status;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

   
}