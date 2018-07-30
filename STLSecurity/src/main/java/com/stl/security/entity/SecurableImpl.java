package com.stl.security.entity;

public class SecurableImpl implements Securable {
    private Token token;

    @Override
    public Token getToken() {
        return this.token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
