package com.auca.service;

import java.util.UUID;

import com.auca.dao.LocationDao;
import com.auca.dao.UserDao;
import com.auca.domain.Location;
import com.auca.domain.User;
import com.auca.util.HibernateUtil;

public class UserService {

    private UserDao userDao = new UserDao(HibernateUtil.buildSessionFactory("application.properties"));
    private LocationDao locationDao = new LocationDao(HibernateUtil.buildSessionFactory("application.properties"));

    public String getProvinceNameByPersonId(UUID personId) {
        User user = userDao.findById(personId);
        if (user == null || user.getVillage() == null) {
            return null;
        }
        Location province = locationDao.findProvinceByVillageId(user.getVillage().getLocationId());
        if (province == null) {
            return null;
        }
        return province.getName();
    }

    public boolean authenticate(String username, String rawPassword) {
        if (username == null || rawPassword == null || username.isBlank() || rawPassword.isBlank()) {
            return false;
        }

        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }

        return rawPassword.equals(user.getPassword());
    }

}
