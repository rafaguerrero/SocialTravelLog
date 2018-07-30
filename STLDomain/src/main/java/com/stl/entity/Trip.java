package com.stl.entity;

import com.stl.security.entity.Securable;
import com.stl.security.entity.SecurableImpl;
import com.stl.utils.UrlUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Trip extends SecurableImpl implements Securable {
    @Id
    protected String id;

    @DBRef
    private Traveler traveler;

    @Indexed(unique = true)
    private String url;

    private String title;
    private String body;
    private List<String> tags;
    private Date creationTime;
    private Date lastModificationTime;

    private Rating rating = new Rating();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
        generateUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        generateUrl();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getUrl() {
        if(this.url == null) {
            throw new RuntimeException("The URL wasn't generated properly");
        }

        return url;
    }

    private void generateUrl() {
        if(this.url == null && this.traveler != null && this.title != null) {
            this.url = UrlUtils.generateUrl(this.title, this.traveler.getUsername());
        }
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(Date lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public Rating getRating() {
        return rating;
    }

    public void rate(Stars stars) {
        rating.rate(stars);
    }
}
