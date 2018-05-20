package com.stl.web.services;

import com.stl.db.TripDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ToolsController {

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(required = false, value = "q", defaultValue = "") String query) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("query", query);
        mav.setViewName("/tools/search");

        return mav;
    }

    @RequestMapping(value = "/tag/{tagName}", method = RequestMethod.GET)
    public ModelAndView tags(@PathVariable String tagName) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("trips", tripDB.getByTag(tagName));
        mav.addObject("tagName", tagName);
        mav.setViewName("/tools/tags");

        return mav;
    }
}