package com.stl.web.services;

import org.springframework.dao.DuplicateKeyException;
import com.stl.db.AuthorDB;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/register")

public class Register {

    @Autowired
    private AuthorDB authorDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createAuthor(@RequestBody @ModelAttribute("author") Author author,
                                     ModelMap model,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/register/index");

        if (author == null) {
            mav.addObject("status", "genericError");

        } else {
            try {
                authorDB.save(author);

                response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "/author/" + author.getUserId());
            } catch (DuplicateKeyException e) {
                mav.addObject("status", "authorAlreadyExists");
            } catch (Exception e) {
                mav.addObject("status", "genericError");
            }
        }

        return mav;
    }


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/register/index", "author", new Author());
    }

}