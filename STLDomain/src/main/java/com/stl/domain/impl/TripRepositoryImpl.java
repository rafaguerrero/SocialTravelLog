package com.stl.domain.impl;

import com.stl.domain.TripRepository;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
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
    public List<Trip> findByTag(String tag) {
        Query query = query(where("tags").all(tag));
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Trip.class);
    }

    @Override
    public List<Trip> findByTraveler(Traveler traveler, int numberOfTrips) {
        Query query = query(where("traveler").is(traveler));
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(numberOfTrips);

        return mongoTemplate.find(query, Trip.class);
    }

    @Override
    public List<Trip> findByTraveler(Traveler traveler) {
        return findByTraveler(traveler, Integer.MAX_VALUE);
    }

    @Override
    public List<Trip> findLatests() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Trip.class);
    }

    @Override
    public List<Trip> findLatests(List<String> travelersUsername) {
        List<Traveler> travelers = mongoTemplate.find(query(where("username").in(travelersUsername)), Traveler.class);

        Query query = new Query();
        query.addCriteria(where("traveler").in(travelers));
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Trip.class);
    }

    @Override
    public void removeAllTripsByTraveler(Traveler traveler) {
        mongoTemplate.remove(query(where("traveler").is(traveler)), Trip.class);
    }
}
