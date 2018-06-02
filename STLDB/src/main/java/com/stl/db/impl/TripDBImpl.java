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
    public List<Trip> getLatestsTrips() {
        return tripRepository.findLatests();
    }

    @Override
    public List<Trip> getLatestsTrips(List<String> travelers) {
        return tripRepository.findLatests(travelers);
    }

    @Override
    public List<Trip> getByTag(String tag) {
        return tripRepository.findByTag(tag);
    }

    @Override
    public Trip getByUrl(String url) {
        return tripRepository.findByUrl(url);
    }

    @Override
    public List<Trip> getByTraveler(Traveler traveler, int numberOfTrips) {
        return tripRepository.findByTraveler(traveler, numberOfTrips);
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

    @Override
    public void removeByTraveler(Traveler traveler) {
        tripRepository.removeAllTripsByTraveler(traveler);
    }
}
