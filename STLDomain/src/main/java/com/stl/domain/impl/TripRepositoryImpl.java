package com.stl.domain.impl;

import com.stl.domain.TripRepository;
import com.stl.entity.Trip;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class TripRepositoryImpl implements TripRepository {
    private static final int MAX_ARTICLES_PAGE = 20;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Trip save(Trip trip) {
        Assert.notNull(trip, "Entity must not be null!");

        mongoTemplate.save(trip);
        return trip;
    }

    @Override
    public Trip findByUrl(String url) {
        Assert.notNull(url, "Url must not be null!");

        return mongoTemplate.findOne(query(where("url").is(url)), Trip.class);
    }

    @Override
    public List<Trip> findByTraveler(Traveler traveler) {
        Query query = query(where("traveler").is(traveler));
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Trip.class);
    }

    @Override
    public List<Trip> findLatestByCreationDate() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Trip.class);
    }
}