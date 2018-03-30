package com.stl.db.impl;

import com.stl.db.TravelerDB;
import com.stl.domain.TravelerRepository;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelerDBImpl implements TravelerDB {
    @Autowired
    private TravelerRepository travelerRepository;

    @Override
    public Traveler getByUsername(String username) {
        return travelerRepository.findByUsername(username);
    }

    @Override
    public void save(Traveler traveler) {
        travelerRepository.save(traveler);
    }
}
