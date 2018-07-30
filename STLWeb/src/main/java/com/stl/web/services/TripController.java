package com.stl.web.services;

import com.stl.db.TripDB;
import com.stl.db.UserDB;
import com.stl.entity.Trip;
import com.stl.security.PermissionRepository;
import com.stl.security.StlRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class TripController {

    @Autowired
    private TripDB tripDB;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserDB userDB;

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
    @PostAuthorize("hasRole('" + StlRole.TRAVELER + "') AND hasPermission(returnObject.modelMap.get('trip').token, 'WRITE')")
    public ModelAndView editArticle(@PathVariable String username,
                                    @PathVariable String path,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Trip trip = tripDB.getByUrl("/" + username + "/" + path);

        if(trip != null) {
            mav.addObject("trip", trip);
            mav.setViewName("/trip/create");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = "/access/{username}/{path}")
    @PreAuthorize("hasRole('" + StlRole.TRAVELER + "')")
    public ModelAndView grandAccess(@PathVariable String username,
                                    @PathVariable String path,
                                    @RequestParam(required = true) String travelerUsername,
                                    HttpServletResponse response) {

        UserDetails travelerUser = userDB.getByUsername(travelerUsername);
        Trip trip = tripDB.getByUrl("/" + username + "/" + path);

        if(travelerUser == null || trip == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }

        permissionRepository.addPermission(travelerUser, trip, BasePermission.WRITE);

        return showArticle(username, path, response);
    }
}
