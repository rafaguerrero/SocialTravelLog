package com.stl.db;

import com.stl.entity.Trip;
import com.stl.entity.Traveler;

import java.util.List;

public interface TripDB {
    List<Trip> getLatestTrips();
    Trip getByUrl(String url);
    List<Trip> getByTraveler(Traveler traveler);

    void create(Trip trip);
    void update(Trip trip);
}
