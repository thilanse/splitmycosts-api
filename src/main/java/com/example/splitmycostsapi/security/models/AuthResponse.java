package com.example.splitmycostsapi.security.models;

import java.io.Serializable;

public class AuthResponse implements Serializable {

    private String jwt;

    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
