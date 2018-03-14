package com.stl.domain;

import com.stl.entity.TravelerData;

public interface TravelerDataRepository {
    TravelerData findByUsername(String username);
    TravelerData save(TravelerData traveler);
}
