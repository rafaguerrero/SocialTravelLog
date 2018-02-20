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
@RequestMapping(value = {"/e"})
public class EditController {

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
    @RequestMapping(value = {"/author", "/author/"}, method = RequestMethod.POST)
    public ModelAndView createAuthor(@RequestBody @ModelAttribute("author") Author author,
                                     ModelMap model,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        if (author == null) {
            mav.addObject("status", "genericError");
            mav.setViewName("/author/create");

        } else {
            try {
                authorDB.save(author);

                mav.addObject("status", "success");
                mav.setViewName("/author/createsuccess");
                response.setStatus(HttpServletResponse.SC_CREATED);

            } catch (DuplicateKeyException e) {
                mav.addObject("status", "authorAlreadyExists");
                mav.setViewName("/author/create");
            } catch (Exception e) {
                mav.addObject("status", "genericError");
                mav.setViewName("/author/create");
            }
        }

        return mav;
    }

    @RequestMapping(value = {"/author", "/author/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/author/create", "author", new Author());
    }

}
