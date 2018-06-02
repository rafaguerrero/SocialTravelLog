package com.stl.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Traveler {
    @Id
    protected String id;

    @Indexed(unique = true)
    String username;

    @Transient
    String password;

    String name;

    TravelerStatus status = TravelerStatus.ACTIVE;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TravelerStatus getStatus() {
        return status;
    }

    public void setStatus(TravelerStatus status) {
        this.status = status;
    }
}
