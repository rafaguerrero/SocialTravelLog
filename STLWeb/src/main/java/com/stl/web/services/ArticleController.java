package com.stl.web.services;

import com.stl.db.ArticleDB;
import com.stl.db.AuthorDB;
import com.stl.domain.ArticleRepository;
import com.stl.domain.AuthorRepository;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody Article article, ModelMap model) {

        // Remove when article creation is complete
        Author author = authorDB.getByUserId(article.getAuthor().getUserId());
        article.setAuthor(author);
        // ---------------

        articleDB.create(article);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createItemForm() {
        return "/article/create";
    }
}
