package com.auca.service;

import java.util.UUID;

import com.auca.dao.BookDao;
import com.auca.dao.ShelfDao;
import com.auca.domain.Book;
import com.auca.domain.Shelf;
import com.auca.util.HibernateUtil;

public class BookService {

    private BookDao bookDao = new BookDao(HibernateUtil.buildSessionFactory("application.properties"));
    private ShelfDao shelfDao = new ShelfDao(HibernateUtil.buildSessionFactory("application.properties"));

    public void assignBookToShelf(UUID bookId, UUID shelfId) {
        Book book = bookDao.findById(bookId);
        Shelf shelf = shelfDao.findById(shelfId);

        book.setShelf(shelf);
        bookDao.update(book);

        shelf.setAvailableStock(shelf.getAvailableStock() + 1);
        shelfDao.update(shelf);
    }

}
