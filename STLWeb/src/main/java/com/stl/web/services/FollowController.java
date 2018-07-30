package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.entity.Traveler;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
public class FollowController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    TripDB tripDB;

    private Traveler getLoggedTraveler(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return travelerDB.getByUsername(user.getUsername());
    }

    @RequestMapping(value = {"/follow/{username}"}, method = RequestMethod.POST)
    public ModelAndView followTraveler(@PathVariable String username,
                                    Authentication authentication) {

        Traveler traveler = getLoggedTraveler(authentication);
        traveler.follow(username);
        travelerDB.update(traveler);

        return new ModelAndView("redirect:/stl/" + username);
    }

    @RequestMapping(value = {"/unfollow/{username}"}, method = RequestMethod.POST)
    public ModelAndView unfollowTraveler(@PathVariable String username,
                                    Authentication authentication) {

        Traveler traveler = getLoggedTraveler(authentication);
        traveler.unfollow(username);
        travelerDB.update(traveler);

        return new ModelAndView("redirect:/stl/" + username);
    }

    @RequestMapping(value = {"/following/{username}"}, method = RequestMethod.GET)
    public ModelAndView followingTraveler(@PathVariable String username,
                                    Authentication authentication) {

        ModelAndView mav = new ModelAndView();
        Traveler traveler = getLoggedTraveler(authentication);

        mav.addObject("trips", tripDB.getLatestsTrips(traveler.getFollowing()));
        mav.setViewName("/home");
        return mav;
    }
}
