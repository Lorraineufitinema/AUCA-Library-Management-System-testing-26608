package com.auca.service;

import java.util.List;
import java.util.UUID;

import com.auca.dao.RoomDao;
import com.auca.dao.ShelfDao;
import com.auca.domain.Room;
import com.auca.domain.Shelf;
import com.auca.util.HibernateUtil;

public class RoomService {

    private RoomDao roomDao = new RoomDao(HibernateUtil.buildSessionFactory("application.properties"));
    private ShelfDao shelfDao = new ShelfDao(HibernateUtil.buildSessionFactory("application.properties"));

    public int countBooksInRoom(UUID roomId) {
        List<Shelf> shelves = shelfDao.findByRoomId(roomId);
        int count = 0;
        for (Shelf shelf : shelves) {
            count += shelf.getAvailableStock();
        }
        return count;
    }

    public Room findRoomWithFewestBooks() {
        List<Room> rooms = roomDao.findAll();
        if (rooms.isEmpty()) {
            return null;
        }

        Room minRoom = null;
        int minCount = Integer.MAX_VALUE;
        for (Room room : rooms) {
            int count = countBooksInRoom(room.getRoomId());
            if (count < minCount) {
                minCount = count;
                minRoom = room;
            }
        }
        return minRoom;
    }

}
