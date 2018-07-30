package com.stl.db.impl;

import com.stl.db.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class UserDBImpl implements UserDB {
    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Override
    public UserDetails getByUsername(String username) {
        return jdbcUserDetailsManager.loadUserByUsername(username);
    }

    @Override
    public void create(User user) {
        jdbcUserDetailsManager.createUser(user);
    }

    @Override
    public void delete(User user) {
        jdbcUserDetailsManager.deleteUser(user.getUsername());
    }
}
