package com.stl.db.impl;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.domain.TravelerRepository;
import com.stl.entity.Traveler;
import com.stl.entity.TravelerStatus;
import com.stl.security.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class TravelerDBImpl implements TravelerDB {
    @Autowired
    private TravelerRepository travelerRepository;

    @Autowired
    private TripDB tripDB;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Traveler getByUsername(String username) {
        return travelerRepository.findByUsername(username);
    }

    @Override
    public void create(Traveler traveler) {
        traveler.setToken(permissionRepository.createToken());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        permissionRepository.createPermission(user, traveler);

        save(traveler);
    }

    @Override
    public void update(Traveler traveler) {
        save(traveler);
    }

    private void save(Traveler traveler) {
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
