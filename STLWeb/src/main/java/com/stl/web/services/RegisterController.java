package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.entity.Traveler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private TravelerDB travelerDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createTraveler(@RequestBody @ModelAttribute("traveler") Traveler traveler,
                                       ModelMap model,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        if (traveler == null) {
            mav.addObject("status", "genericError");
            mav.setViewName("/traveler/create");

        } else {
            try {
                travelerDB.save(traveler);

                mav.addObject("status", "success");
                mav.setViewName("/traveler/createsuccess");
                response.setStatus(HttpServletResponse.SC_CREATED);

            } catch (DuplicateKeyException e) {
                mav.addObject("status", "authorAlreadyExists");
                mav.setViewName("/traveler/create");
            } catch (Exception e) {
                mav.addObject("status", "genericError");
                mav.setViewName("/traveler/create");
            }
        }

        return mav;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/traveler/create", "traveler", new Traveler());
    }

}
