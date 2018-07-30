package com.stl.db.impl;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.domain.TripRepository;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
import com.stl.security.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TripDBImpl implements TripDB {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private TravelerDB travelerDB;

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
        trip.setToken(permissionRepository.createToken());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        permissionRepository.createPermission(user, trip);

        save(trip);
    }

    @Override
    @PreAuthorize("hasPermission(trip.getToken(), 'WRITE')")
    public void update(Trip trip) {
        trip.setLastModificationTime(new Date());

        save(trip);
    }

    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    @Override
    public void removeByTraveler(Traveler traveler) {
        tripRepository.removeAllTripsByTraveler(traveler);
    }
}
