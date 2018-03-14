package com.stl.db.impl;

import com.stl.db.TravelerDataDB;
import com.stl.domain.TravelerDataRepository;
import com.stl.entity.TravelerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TravelerDataDBImpl implements TravelerDataDB {
    @Autowired
    private TravelerDataRepository travelerDataRepository;

    @Override
    public TravelerData getByUsername(String username) {
        return travelerDataRepository.findByUsername(username);
    }

    @Override
    public void save(TravelerData traveler) {
        travelerDataRepository.save(traveler);
    }
}
