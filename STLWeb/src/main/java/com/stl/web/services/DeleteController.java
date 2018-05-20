package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/d", method = RequestMethod.POST)
public class DeleteController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private UserDB userDB;

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ModelAndView deleteTraveler(Authentication authentication,
                                       HttpServletRequest request) throws ServletException {

        User user = (User) authentication.getPrincipal();
        travelerDB.delete(user.getUsername());
        userDB.delete(user);

        request.logout();

        return new ModelAndView("/traveler/delete.success");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView deleteTravelerForm() {
        return new ModelAndView("/traveler/delete");
    }
}
