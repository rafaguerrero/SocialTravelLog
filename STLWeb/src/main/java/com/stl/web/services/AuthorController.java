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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@RequestBody Author author, ModelMap model) {
        authorDB.save(author);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAuthorForm() {
        return "/author/create";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView getAuthorPage(@PathVariable String userId,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        Author author = authorDB.getByUserId(userId);

        if(author != null) {
            List<Article> article = articleDB.getByAuthor(author);

            mav.addObject("author", author);
            mav.addObject("articles", article);
            mav.setViewName("/author/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }
}
