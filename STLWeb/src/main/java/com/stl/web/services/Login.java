package com.stl.web.services;

import com.stl.db.AuthorDB;
import com.stl.entity.Author;
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
@RequestMapping(value = "/login")

public class Login {

    @Autowired
    private AuthorDB authorDB;

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ModelAndView createAuthor(@RequestBody @ModelAttribute("author") Author author,
                                     ModelMap model,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        if (author == null) {
            mav.addObject("status", "genericError");
            mav.setViewName("/login");

        } else {
            try {
                authorDB.save(author);

                mav.addObject("status", "success");
                mav.setViewName("/login/loginsuccess"); /*ir al perfil del escritor*/
                response.setStatus(HttpServletResponse.SC_CREATED);

            } catch (DuplicateKeyException e) {
                mav.addObject("status", "authorAlreadyExists");
                mav.setViewName("/login");
            } catch (Exception e) {
                mav.addObject("status", "genericError");
                mav.setViewName("/login");
            }
        }

        return mav;
    }


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String createAuthorForm() {
        return "/login/index";
    }
}