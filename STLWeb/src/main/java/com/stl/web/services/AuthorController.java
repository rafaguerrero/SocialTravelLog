package com.stl.web.services;

import org.springframework.dao.DuplicateKeyException;
import com.stl.db.ArticleDB;
import com.stl.db.TravelerDataDB;
import com.stl.entity.Article;
import com.stl.entity.TravelerData;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TravelerDataDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createTraveler(@RequestBody @ModelAttribute("traveler") TravelerData traveler,
                                       ModelMap model,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        if (traveler == null) {
            mav.addObject("status", "genericError");
            mav.setViewName("/traveler/create");

        } else {
            try {
                authorDB.save(traveler);

                mav.addObject("status", "success");
                mav.setViewName("/traveler/createsuccess");
                response.setStatus(HttpServletResponse.SC_CREATED);

            } catch (DuplicateKeyException e) {
                mav.addObject("status", "authorAlreadyExists");
                mav.setViewName("/traveler/create");
            } catch (Exception e) {
                mav.addObject("status", "genericError");
                mav.setViewName("/traveler/create");
            }
        }

        return mav;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/traveler/create", "traveler", new TravelerData());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView getAuthorPage(@PathVariable String userId,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        TravelerData traveler = authorDB.getByUsername(userId);

        if(traveler != null) {
            List<Article> article = articleDB.getByTravler(traveler);

            mav.addObject("author", traveler);
            mav.addObject("articles", article);
            mav.setViewName("/traveler/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }
}
