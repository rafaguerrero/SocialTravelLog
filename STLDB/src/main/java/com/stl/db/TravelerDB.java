package com.stl.db;

import com.stl.entity.Traveler;

public interface TravelerDB {
    Traveler getByUsername(String username);

    void save(Traveler traveler);
}
