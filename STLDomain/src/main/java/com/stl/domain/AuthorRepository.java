package com.stl.domain;

import com.stl.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author findByUserId(String userId);
    Author save(Author author);
}
