package com.stl.domain.impl;

import com.stl.domain.AuthorRepository;
import com.stl.entity.Article;
import com.stl.entity.Author;
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
public class AuthorRepositoryImpl implements AuthorRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Author findByUserId(String userId) {
        return mongoTemplate.findOne(query(where("userId").is(userId)), Author.class);
    }

    @Override
    public Author save(Author author) {
        Assert.notNull(author, "Entity must not be null!");

        mongoTemplate.save(author);
        return author;
    }
}
