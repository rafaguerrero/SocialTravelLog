package com.stl.web.services;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/homepage")
public class HomepageController {

    @RequestMapping(value = {"", "/"})
    public String getHomepage() {
        return "/home";
    }
}
