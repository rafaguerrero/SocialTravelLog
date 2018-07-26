package com.stl.entity;

import com.stl.security.entity.Securable;
import com.stl.security.entity.SecurableImpl;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
public class Traveler extends SecurableImpl implements Securable {
    @Id
    protected String id;

    @Indexed(unique = true)
    String username;

    @Transient
    String password;

    String name;

    TravelerStatus status = TravelerStatus.ACTIVE;

    List<String> following = new ArrayList<String>();
    Map<String, Stars> rated = new HashMap<>();

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

    public List<String> getFollowing() {
        return following;
    }

    public void follow(String traveler) {
        this.following.add(traveler);
    }

    public void unfollow(String traveler) {
        this.following.remove(traveler);
    }

    public Map<String, Stars> getRated() {
        return rated;
    }

    public void rate(String trip, Stars stars) {
        rated.put(trip, stars);
    }
}
