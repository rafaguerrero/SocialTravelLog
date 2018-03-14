package com.stl.tags;

import com.stl.db.TravelerDB;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class TravelerDataTag extends SimpleTagSupport {

    @Autowired
    private TravelerDB travelerDB;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void doTag() throws JspException, IOException {
        getJspContext().setAttribute("traveler", travelerDB.getByUsername(username), PageContext.PAGE_SCOPE);
        super.doTag();
        getJspContext().removeAttribute("traveler", PageContext.PAGE_SCOPE);
    }
}
