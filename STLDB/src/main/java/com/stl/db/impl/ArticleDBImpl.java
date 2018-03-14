package com.stl.db.impl;

import com.stl.db.ArticleDB;
import com.stl.domain.ArticleRepository;
import com.stl.entity.Article;
import com.stl.entity.TravelerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ArticleDBImpl implements ArticleDB {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public List<Article> getLatestArticles() {
        return articleRepository.findLatestByCreationDate();
    }

    @Override
    public Article getByUrl(String url) {
        return articleRepository.findByUrl(url);
    }

    @Override
    public List<Article> getByTravler(TravelerData traveler) {
        return articleRepository.findByTraveler(traveler);
    }

    @Override
    public void create(Article article) {
        Date now = new Date();
        article.setCreationTime(now);
        article.setLastModificationTime(now);

        articleRepository.save(article);
    }

    @Override
    public void update(Article article) {
        article.setLastModificationTime(new Date());

        articleRepository.save(article);
    }
}
