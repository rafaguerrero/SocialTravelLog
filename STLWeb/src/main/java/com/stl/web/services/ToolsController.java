package com.stl.web.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/tools")
public class ToolsController {

    @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
    public ModelAndView search() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("toolName", "search");
        mav.setViewName("/tools/default");
        return mav;
    }

    @RequestMapping(value = "/tags/{tagName}", method = RequestMethod.GET)
    public ModelAndView tags(@PathVariable String tagName,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        mav.addObject("toolName", "tags");
        mav.addObject("tagName", tagName);
        mav.setViewName("/tools/default");
        return mav;
    }
}