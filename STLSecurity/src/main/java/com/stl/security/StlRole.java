package com.stl.security;

import org.springframework.security.core.GrantedAuthority;

public enum StlRole implements GrantedAuthority {
    ROLE_TRAVELER(StlRole.TRAVELER),
    ROLE_ADMIN(StlRole.ADMIN);

    public static final String TRAVELER = "ROLE_TRAVELER";
    public static final String ADMIN = "ROLE_ADMIN";

    String authority;

    StlRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
