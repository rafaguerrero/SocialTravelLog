package com.stl.tags;

import com.stl.db.TravelerDB;
import com.stl.entity.Traveler;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class TravelerDataTag extends STLTag {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void doTag() throws JspException, IOException {
        Traveler traveler = getTravelerDB().getByUsername(username);

        getJspContext().setAttribute("traveler", traveler, PageContext.PAGE_SCOPE);
        renderJspBody();
        getJspContext().removeAttribute("traveler", PageContext.PAGE_SCOPE);
    }

    private TravelerDB getTravelerDB() {
        return (TravelerDB) getFromApplicationContext(TravelerDB.class);
    }
}
