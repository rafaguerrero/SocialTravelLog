package com.stl.web.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(required = false, value = "q", defaultValue = "") String query) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("query", query);
        mav.setViewName("/tools/search");

        return mav;
    }
}