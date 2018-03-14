package com.stl.db;

import com.stl.entity.TravelerData;

public interface TravelerDataDB {
    TravelerData getByUsername(String username);

    void save(TravelerData traveler);
}
