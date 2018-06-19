package com.stl.web.services;

import com.stl.db.TravelerDB;
import com.stl.db.TripDB;
import com.stl.db.UserDB;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
@RequestMapping(value = "/d")
public class DeleteController {

    @Autowired
    private TravelerDB travelerDB;

    @Autowired
    private UserDB userDB;

    @Autowired
    private TripDB tripDB;

    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ModelAndView deleteTraveler(@RequestParam(required = false) String deleteArticles,
                                       Authentication authentication,
                                       HttpServletRequest request) throws ServletException {

        User user = (User) authentication.getPrincipal();
        userDB.delete(user);
        travelerDB.delete(user.getUsername(), deleteArticles != null);

        request.logout();

        return new ModelAndView("/traveler/delete.success");
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView deleteTravelerForm() {
        return new ModelAndView("/traveler/delete");
    }
}
