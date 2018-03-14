package com.stl.web.services;

import com.stl.db.TripDB;
import com.stl.db.TravelerDB;
import com.stl.entity.Trip;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = {"/{userId}/new_trip"}, method = RequestMethod.POST)
    public ModelAndView saveTrip(@PathVariable String userId,
                                    @RequestBody Trip trip,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);
        ModelAndView mav = new ModelAndView();
        int status;

        if(traveler != null) {
            trip.setTraveler(traveler);
            tripDB.create(trip);

            status = HttpServletResponse.SC_CREATED;
        } else {
            status = HttpServletResponse.SC_NOT_FOUND;
        }

        response.setStatus(status);
        mav.addObject("status", HttpServletResponse.SC_NOT_FOUND);
        return mav;
    }

    @RequestMapping(value = {"/{userId}/new_trip"}, method = RequestMethod.GET)
    public ModelAndView createTrip(@PathVariable String userId,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);

        ModelAndView mav = new ModelAndView();

        if(traveler == null) {
            int errorStatus = HttpServletResponse.SC_NOT_FOUND;

            response.setStatus(errorStatus);
            mav.addObject("errorStatus", errorStatus);
            mav.setViewName("/error");
            return mav;
        }

        mav.setViewName("/trip/create");
        return mav;
    }
}
