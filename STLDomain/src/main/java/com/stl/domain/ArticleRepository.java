package com.stl.domain;

import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;


public interface ArticleRepository {
    List<Article> findByAuthor(Author author);
    Article save(Article article);
}
