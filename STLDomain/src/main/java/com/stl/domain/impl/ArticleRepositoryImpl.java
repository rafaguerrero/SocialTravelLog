package com.stl.domain.impl;

import com.stl.domain.ArticleRepository;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
    private static final int MAX_ARTICLES_PAGE = 20;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Article save(Article article) {
        Assert.notNull(article, "Entity must not be null!");

        mongoTemplate.save(article);
        return article;
    }

    @Override
    public Article findByUrl(String url) {
        Assert.notNull(url, "Url must not be null!");

        return mongoTemplate.findOne(query(where("url").is(url)), Article.class);
    }

    @Override
    public List<Article> findByAuthor(Author author) {
        Query query = query(where("author").is(author));
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);


        return mongoTemplate.find(query, Article.class);
    }

    @Override
    public List<Article> findLatestByCreationDate() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creationTime"));
        query.limit(MAX_ARTICLES_PAGE);

        return mongoTemplate.find(query, Article.class);
    }
}
