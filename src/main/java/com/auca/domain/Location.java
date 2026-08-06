package com.auca.domain;

import java.util.List;
import java.util.UUID;

import com.auca.enums.LocationType;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    private UUID locationId;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Location parentLocation;

    @OneToMany(mappedBy = "parentLocation")
    private List<Location> children;

    public UUID getLocationId() {
        return locationId;
    }
    

    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationType locationType) {
        this.locationType = locationType;
    }

    public Location getParentLocation() {
        return parentLocation;
    }

    public void setParentLocation(Location parentLocation) {
        this.parentLocation = parentLocation;
    }

    public List<Location> getChildren() {
        return children;
    }

    public void setChildren(List<Location> children) {
        this.children = children;
    }

    
}