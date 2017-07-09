package com.stl.domain.impl;

import com.stl.domain.ArticleRepository;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Article save(Article article) {
        Assert.notNull(article, "Entity must not be null!");

        mongoTemplate.save(article);
        return article;
    }

    @Override
    public List<Article> findByAuthor(Author author) {
        return mongoTemplate.find(query(where("author").is(author)), Article.class);
    }
}
