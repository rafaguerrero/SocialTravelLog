package com.stl.web.services;

import com.stl.db.ArticleDB;
import com.stl.db.AuthorDB;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/author/{userId}/post")
public class ArticleCreationController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView saveArticle(@PathVariable String userId,
                            @RequestBody Article article,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        Author author = authorDB.getByUserId(userId);
        ModelAndView mav = new ModelAndView();
        int status;

        if(author != null) {
            article.setAuthor(author);
            articleDB.create(article);

            status = HttpServletResponse.SC_CREATED;
        } else {
            status = HttpServletResponse.SC_NOT_FOUND;
        }

        response.setStatus(status);
        mav.addObject("status", HttpServletResponse.SC_NOT_FOUND);
        return mav;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createArticle(@PathVariable String userId,
                                HttpServletRequest request,
                                HttpServletResponse response) {

        Author author = authorDB.getByUserId(userId);

        ModelAndView mav = new ModelAndView();

        if(author == null) {
            int errorStatus = HttpServletResponse.SC_NOT_FOUND;

            response.setStatus(errorStatus);
            mav.addObject("errorStatus", errorStatus);
            mav.setViewName("/error");
            return mav;
        }

        mav.setViewName("/views/article/create");
        return mav;
    }
}
