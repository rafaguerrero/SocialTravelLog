package com.stl.web.services;

import org.springframework.dao.DuplicateKeyException;
import com.stl.db.ArticleDB;
import com.stl.db.AuthorDB;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = {"/{userId}","/{userId}/"})
public class PublishController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = {"/article", "/article/"}, method = RequestMethod.POST)
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

    @RequestMapping(value = {"/article", "/article/"}, method = RequestMethod.GET)
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

        mav.setViewName("/article/create");
        return mav;
    }

}
