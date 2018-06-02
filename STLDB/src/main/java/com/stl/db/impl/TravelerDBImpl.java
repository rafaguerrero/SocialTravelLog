package com.stl.db.impl;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.domain.TravelerRepository;
import com.stl.entity.Traveler;
import com.stl.entity.TravelerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelerDBImpl implements TravelerDB {
    @Autowired
    private TravelerRepository travelerRepository;

    @Autowired
    private TripDB tripDB;

    @Override
    public Traveler getByUsername(String username) {
        return travelerRepository.findByUsername(username);
    }

    @Override
    public void save(Traveler traveler) {
        travelerRepository.save(traveler);
    }

    @Override
    public void delete(String username, boolean deleteArticles) {
        Traveler traveler = getByUsername(username);

        travelerRepository.delete(traveler);

        if(deleteArticles) {
            tripDB.removeByTraveler(traveler);
        } else {
            traveler.setStatus(TravelerStatus.INACTIVE);
            save(traveler);
        }
    }
}
