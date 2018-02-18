package com.stl.web.services;

import com.stl.db.ArticleDB;
import com.stl.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PageControler {

    @Autowired
    private ArticleDB articleDB;

    @RequestMapping(value = "/{userId}/{path}")
    public ModelAndView showArticle(@PathVariable String userId,
                                    @PathVariable String path,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Article article = articleDB.getByUrl("/" + userId + "/" + path);
        if(article != null) {
            mav.addObject("article", article);

            mav.setViewName("/article/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }
}
