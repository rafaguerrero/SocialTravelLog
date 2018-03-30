package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller

    @RequestMapping(value = "/tools")
    public class ToolsController {

        @Autowired
        private TravelerDB travelerDB;

        @Autowired
        private TripDB tripDB;

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("toolName", "search");
        mav.setViewName("/tools/default");
        return mav;
    }

    @RequestMapping(value = "/tags/{tagName}", method = RequestMethod.GET)
    public ModelAndView tags(@PathVariable String tagName,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        List<Trip> trip = tripDB.getByTag(tagName);

        ModelAndView mav = new ModelAndView();
        mav.addObject("toolName", "tags");
        mav.addObject("trips", trip);
        mav.addObject("tagName", tagName);
        mav.setViewName("/tools/tags");
        return mav;
    }
}