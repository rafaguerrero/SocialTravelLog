package com.stl.db;

import com.stl.entity.Traveler;

public interface TravelerDB {
    Traveler getByUsername(String username);

    void create(Traveler traveler);
    void update(Traveler traveler);
    void delete(String username, boolean deleteArticles);
}
