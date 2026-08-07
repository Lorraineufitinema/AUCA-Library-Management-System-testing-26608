package com.auca.service;

import java.util.UUID;

import com.auca.dao.RoomDao;
import com.auca.dao.ShelfDao;
import com.auca.domain.Room;
import com.auca.domain.Shelf;
import com.auca.util.HibernateUtil;

public class ShelfService {

    private ShelfDao shelfDao = new ShelfDao(HibernateUtil.buildSessionFactory("application.properties"));
    private RoomDao roomDao = new RoomDao(HibernateUtil.buildSessionFactory("application.properties"));

    public void assignShelfToRoom(UUID shelfId, UUID roomId) {
        Shelf shelf = shelfDao.findById(shelfId);
        Room room = roomDao.findById(roomId);

        shelf.setRoom(room);
        shelfDao.update(shelf);
    }

}
