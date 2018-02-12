package com.stl.web.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminControler {

    @RequestMapping(value = "/admin")
    public ModelAndView loggedContent(HttpServletRequest request,
                                      HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/admin");
        return mav;
    }
}
