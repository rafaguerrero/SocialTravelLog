package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
                                    @RequestBody @ModelAttribute("trip") Trip trip,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);

        if(traveler == null) {
            int errorStatus = HttpServletResponse.SC_NOT_FOUND;

            response.setStatus(errorStatus);
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorStatus", errorStatus);
            mav.setViewName("/error");
            return mav;
        }

        trip.setTraveler(traveler);
        tripDB.create(trip);

        return new ModelAndView("redirect:" + trip.getUrl());
    }

    @RequestMapping(value = {"/{userId}/new_trip"}, method = RequestMethod.GET)
    public ModelAndView createTrip(@PathVariable String userId,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("trip", new Trip());

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
