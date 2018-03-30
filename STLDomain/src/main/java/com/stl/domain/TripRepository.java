package com.stl.domain;

import com.stl.entity.Trip;
import com.stl.entity.Traveler;

import java.util.List;


public interface TripRepository {
    List<Trip> findLatestByCreationDate();
    List<Trip> findByTraveler(Traveler traveler);
    Trip findByUrl(String url);
    Trip save(Trip trip);
    List<Trip> findByTag(String tag);
}
