package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Stars;
import com.stl.entity.Traveler;
import com.stl.entity.Trip;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
@RequestMapping(value = {"/rate"})
public class RateController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    TripDB tripDB;

    private Traveler getLoggedTraveler(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return travelerDB.getByUsername(user.getUsername());
    }

    @RequestMapping(value = "/{username}/{path}", method = RequestMethod.POST)
    public ModelAndView followTraveler(@PathVariable String username,
                                       @PathVariable String path,
                                       @RequestParam Stars stars,
                                       Authentication authentication) {

        String url = "/" + username + "/" + path;

        Trip trip = tripDB.getByUrl(url);
        trip.rate(stars);
        tripDB.update(trip);

        Traveler traveler = getLoggedTraveler(authentication);
        traveler.rate(trip.getId(), stars);
        travelerDB.update(traveler);

        return new ModelAndView("redirect:/stl" + url);
    }
}
