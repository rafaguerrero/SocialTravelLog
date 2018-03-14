package com.stl.web.services;

import com.stl.db.TripDB;
import com.stl.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/{userId}/{path}")
public class TripController {

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "")
    public ModelAndView showArticle(@PathVariable String userId,
                                    @PathVariable String path,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Trip trip = tripDB.getByUrl("/" + userId + "/" + path);
        if(trip != null) {
            mav.addObject("article", trip);

            mav.setViewName("/trip/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }
}
