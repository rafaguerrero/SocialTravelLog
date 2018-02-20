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
@RequestMapping(value = "/tools")
public class ToolsController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
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

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/tools", "author", new Author());
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
