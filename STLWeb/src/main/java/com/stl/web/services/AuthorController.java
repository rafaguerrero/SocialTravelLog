package com.stl.web.services;

import com.stl.domain.ArticleRepository;
import org.springframework.dao.DuplicateKeyException;
import com.stl.db.ArticleDB;
import com.stl.db.AuthorDB;
import com.stl.entity.Article;
import com.stl.entity.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = {"/{userId}"})
public class AuthorController {

    @Autowired
    private AuthorDB authorDB;

    @Autowired
    private ArticleDB articleDB;

    @Autowired
    private ArticleRepository articleRepository;


    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public ModelAndView getAuthorPage(@PathVariable String userId,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();

        Author author = authorDB.getByUserId(userId);

        if(author != null) {
            List<Article> article = articleDB.getByAuthor(author);

            mav.addObject("author", author);
            mav.addObject("articles", article);
            mav.setViewName("/author/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }

    @RequestMapping(value = {"/{path}","/{path}/"})
    public ModelAndView showArticle(@PathVariable String userId,
                                    @PathVariable String path,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();

        Article article = articleRepository.findByUrl("/" + userId + "/" + path);
        if(article != null) {
            mav.addObject("article", article);

            mav.setViewName("/article/page");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return mav;
    }

}
