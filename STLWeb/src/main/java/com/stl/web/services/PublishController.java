package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
@RequestMapping(value = {"/trip"})
public class PublishController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = {"/{userId}"}, method = RequestMethod.POST)
    public ModelAndView saveTrip(@PathVariable String userId,
                                    @RequestBody @ModelAttribute("trip") Trip trip,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);

        if(traveler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        try {
            trip.setTraveler(traveler);
            tripDB.create(trip);
        } catch(DuplicateKeyException e) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("errorStatus", "tripAlreadyExists");
            mav.setViewName("/trip/create");
            return mav;
        }

        return new ModelAndView("redirect:/stl" + trip.getUrl());
    }

    @RequestMapping(value = {"/{userId}"}, method = RequestMethod.GET)
    public ModelAndView createTrip(@PathVariable String userId,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {

        Traveler traveler = travelerDB.getByUsername(userId);

        ModelAndView mav = new ModelAndView();
        mav.addObject("trip", new Trip());

        if(traveler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        mav.setViewName("/trip/create");
        return mav;
    }
}
