package com.stl.domain;

import com.stl.entity.Traveler;

public interface TravelerRepository {
    Traveler findByUsername(String username);
    Traveler save(Traveler traveler);
    void delete(Traveler traveler);
}
