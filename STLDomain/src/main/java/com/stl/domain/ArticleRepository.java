package com.stl.domain;

import com.stl.entity.Article;
import com.stl.entity.TravelerData;

import java.util.List;


public interface ArticleRepository {
    List<Article> findLatestByCreationDate();
    List<Article> findByTraveler(TravelerData traveler);
    Article findByUrl(String url);
    Article save(Article article);
}
