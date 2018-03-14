package com.stl.db.impl;

import com.stl.db.TripDB;
import com.stl.domain.TripRepository;
import com.stl.entity.Trip;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TripDBImpl implements TripDB {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public List<Trip> getLatestTrips() {
        return tripRepository.findLatestByCreationDate();
    }

    @Override
    public Trip getByUrl(String url) {
        return tripRepository.findByUrl(url);
    }

    @Override
    public List<Trip> getByTraveler(Traveler traveler) {
        return tripRepository.findByTraveler(traveler);
    }

    @Override
    public void create(Trip trip) {
        Date now = new Date();
        trip.setCreationTime(now);
        trip.setLastModificationTime(now);

        tripRepository.save(trip);
    }

    @Override
    public void update(Trip trip) {
        trip.setLastModificationTime(new Date());

        tripRepository.save(trip);
    }
}
