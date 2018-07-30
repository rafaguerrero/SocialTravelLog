package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.UserDB;
import com.stl.entity.Traveler;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private UserDB userDB;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createTraveler(@RequestBody @ModelAttribute("traveler") Traveler traveler,
                                       ModelMap model,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        if (traveler == null) {
            mav.addObject("status", "genericError");
            mav.setViewName("/traveler/register");

        } else {
            try {
                User user = userService.createUserFromTraveler(traveler, StlRole.TRAVELER);
                userDB.create(user);

                userService.logIn(user);

                travelerDB.create(traveler);

                mav.addObject("status", "success");
                mav.addObject("traveler", traveler);
                mav.setViewName("/traveler/register.success");
                response.setStatus(HttpServletResponse.SC_CREATED);
            } catch (Exception e) {
                mav.addObject("status", getErrorStatus(e));
                mav.setViewName("/traveler/register");
            }
        }

        return mav;
    }

    private String getErrorStatus(Exception e) {
        return e instanceof DuplicateKeyException ? "authorAlreadyExists" : "genericError";
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView createAuthorForm() {
        return new ModelAndView("/traveler/register", "traveler", new Traveler());
    }
}
