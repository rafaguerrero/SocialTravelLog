package com.stl.web.services;

import com.stl.db.TripDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"", "/"})
public class HomepageController {

    @Autowired
    TripDB tripDB;

    @RequestMapping(value = "")
    public ModelAndView getHomepage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("trips", tripDB.getLatestsTrips());
        mav.setViewName("/home");
        return mav;
    }
}
