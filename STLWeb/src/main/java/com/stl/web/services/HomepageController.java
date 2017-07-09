package com.stl.web.services;

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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home")
public class HomepageController {

    @RequestMapping(value = {"", "/"})
    public ModelAndView getHomepage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/home");
        return mav;
    }
}
