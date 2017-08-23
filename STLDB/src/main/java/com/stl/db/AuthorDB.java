package com.stl.db;

import com.stl.entity.Article;
import com.stl.entity.Author;

import java.util.List;

public interface AuthorDB {
    Author getByUserId(String userId);

    void save(Author author);
}
