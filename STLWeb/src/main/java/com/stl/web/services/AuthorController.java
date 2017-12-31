package com.stl.web.services;

import com.stl.db.ArticleDB;
import com.stl.db.AuthorDB;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createAuthor(@RequestBody @ModelAttribute("author") Author author,
                                     BindingResult result,
                                     ModelMap model,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.addObject("status", "error");
            mav.setViewName("/views/author/create");
        }else {

            if (author == null) {
                mav.addObject("status", "error");
                mav.setViewName("/views/author/create");

            } else {
                authorDB.save(author);

                mav.addObject("status", "sucess");
                mav.setViewName("/views/author/createsuccess");
                response.setStatus(HttpServletResponse.SC_CREATED);
            }


        }
        return mav;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/views/author/create", "author", new Author());
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
            mav.setViewName("/views/author/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }
}
