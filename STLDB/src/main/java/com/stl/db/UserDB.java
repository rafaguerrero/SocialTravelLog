package com.stl.db;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDB {
    UserDetails getByUsername(String username);
    void create(User user);
    void delete(User user);
}
