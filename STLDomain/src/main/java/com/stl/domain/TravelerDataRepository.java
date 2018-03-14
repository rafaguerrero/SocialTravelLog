package com.stl.domain;

import com.stl.entity.Traveler;

public interface TravelerDataRepository {
    Traveler findByUsername(String username);
    Traveler save(Traveler traveler);
}
