package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/stl")
public class TravelerController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView getTravelerPage(@PathVariable String username,
                                        HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Traveler traveler = travelerDB.getByUsername(username);

        if(traveler != null) {
            mav.addObject("trips", tripDB.getByTraveler(traveler));
            mav.addObject("traveler", traveler);
            mav.setViewName("/traveler/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            mav.setViewName("/error");
        }

        return mav;
    }
}
