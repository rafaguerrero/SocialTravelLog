package com.stl.db.impl;

import com.stl.db.AuthorDB;
import com.stl.domain.ArticleRepository;
import com.stl.domain.AuthorRepository;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDBImpl implements AuthorDB {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author getByUserId(String userId) {
        return authorRepository.findByUserId(userId);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }
}
