package com.stl.web.services;

import com.stl.db.TripDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/tag")
public class TagsController {

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "/{tagName}", method = RequestMethod.GET)
    public ModelAndView tags(@PathVariable String tagName) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("trips", tripDB.getByTag(tagName));
        mav.addObject("tagName", tagName);
        mav.setViewName("/tools/tags");

        return mav;
    }
}