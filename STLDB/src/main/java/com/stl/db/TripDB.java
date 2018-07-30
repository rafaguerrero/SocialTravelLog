package com.stl.db;

import com.stl.entity.Trip;
import com.stl.entity.Traveler;

import java.util.List;

public interface TripDB {
    Trip getByUrl(String url);
    List<Trip> getLatestsTrips();
    List<Trip> getLatestsTrips(List<String> travelers);
    List<Trip> getByTraveler(Traveler traveler);
    List<Trip> getByTraveler(Traveler traveler, int numberOfTrips);
    List<Trip> getByTag(String tag);

    void create(Trip trip);
    void update(Trip trip);

    void removeByTraveler(Traveler traveler);
}
