package com.auca.domain;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "shelf")
public class Shelf {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID shelfId;

    private String shelfCode;

    private int availableStock;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "shelf")
    private List<Book> books;

    public Shelf() {
    }

    public Shelf(UUID shelfId, String shelfCode, int availableStock) {
        this.shelfId = shelfId;
        this.shelfCode = shelfCode;
        this.availableStock = availableStock;
    }

    public UUID getShelfId() {
        return shelfId;
    }

    public void setShelfId(UUID shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

   
}