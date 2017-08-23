package com.stl.db;

import com.stl.entity.Article;
import com.stl.entity.Author;

import java.util.List;

public interface ArticleDB {
    List<Article> getLatestArticles();
    Article getByUrl(String url);
    List<Article> getByAuthor(Author author);

    void create(Article article);
    void update(Article article);
}
