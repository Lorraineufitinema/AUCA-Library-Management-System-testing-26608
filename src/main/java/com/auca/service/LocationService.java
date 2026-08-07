package com.auca.service;

import java.util.List;
import java.util.UUID;

import com.auca.dao.LocationDao;
import com.auca.domain.Location;
import com.auca.enums.LocationType;
import com.auca.util.HibernateUtil;

public class LocationService {

    private LocationDao locationDao = new LocationDao(HibernateUtil.buildSessionFactory("application.properties"));

    public Location createLocation(Location location, UUID parentId) {
        if (parentId != null) {
            Location parent = locationDao.findById(parentId);
            if (parent == null) {
                throw new RuntimeException("Parent with that code is not there");
            }
            location.setParentLocation(parent);
        }

        if (locationDao.findByCode(location.getCode()) != null) {
            throw new RuntimeException("Duplicate location code");
        }

        locationDao.save(location);
        return location;
    }

    public String getProvinceNameByVillageId(UUID villageId) {
        Location province = locationDao.findProvinceByVillageId(villageId);
        if (province == null) {
            return null;
        }
        return province.getName();
    }

}
