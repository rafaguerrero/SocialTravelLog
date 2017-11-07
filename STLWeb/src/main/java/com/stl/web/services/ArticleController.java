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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void saveArticle(@RequestBody Article article,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        Author author = authorDB.getByUserId(article.getAuthor().getUserId());

        if(author != null) {
            article.setAuthor(author);
            articleDB.create(article);

            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createArticle() {
        return "/article/create";
    }
}
