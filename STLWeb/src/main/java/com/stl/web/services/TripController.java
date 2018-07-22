package com.stl.web.services;

import com.stl.db.TripDB;
import com.stl.entity.Trip;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TripController {

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "/stl/{username}/{path}")
    public ModelAndView showArticle(@PathVariable String username,
                                    @PathVariable String path,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Trip trip = tripDB.getByUrl("/" + username + "/" + path);
        if(trip != null) {
            mav.addObject("traveler", trip.getTraveler());
            mav.addObject("trip", trip);

            mav.setViewName("/trip/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = "/edit/{username}/{path}")
    @PostAuthorize("hasRole('" + StlRole.TRAVELER + "')")
    public ModelAndView editArticle(@PathVariable String username,
                                    @PathVariable String path,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Trip trip = getSecureTrip("/" + username + "/" + path);

        if(trip != null) {
            mav.addObject("trip", trip);
            mav.setViewName("/trip/create");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }

    @PostAuthorize("hasPermission(returnObject.getToken(), 'WRITE')")
    private Trip getSecureTrip(String url) {
        return tripDB.getByUrl(url);
    }
}
