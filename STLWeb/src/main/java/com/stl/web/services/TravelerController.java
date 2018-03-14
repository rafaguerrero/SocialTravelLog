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
public class TravelerController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView getTravelerPage(@PathVariable String userId,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        Traveler traveler = travelerDB.getByUsername(userId);

        if(traveler != null) {
            List<Trip> trip = tripDB.getByTraveler(traveler);

            mav.addObject("author", traveler);
            mav.addObject("articles", trip);
            mav.setViewName("/traveler/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            mav.setViewName("/error");
        }

        return mav;
    }
}
