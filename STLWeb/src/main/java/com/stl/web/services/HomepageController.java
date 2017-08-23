package com.stl.web.services;

import com.stl.db.ArticleDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomepageController {

    @Autowired
    ArticleDB articleDB;

    @RequestMapping(value = {"", "/"})
    public ModelAndView getHomepage() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("articles", articleDB.getLatestArticles());
        mav.setViewName("/home");


        return mav;
    }
}
