package com.stl.tags;

import com.stl.db.TravelerDB;
import com.stl.entity.Traveler;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class TravelerDataTag extends SimpleTagSupport {
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
        super.doTag();
        getJspContext().removeAttribute("traveler", PageContext.PAGE_SCOPE);
    }

    private TravelerDB getTravelerDB() {
        ServletRequest request = ((PageContext) getJspContext()).getRequest();
        return (TravelerDB) RequestContextUtils.getWebApplicationContext(request).getBean("travelerDB");
    }
}
