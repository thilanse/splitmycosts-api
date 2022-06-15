package com.example.splitmycostsapi.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJWTUtil {

//    @Test
//    public void test_generateToken(){
//        JWTUtil jwtUtil = new JWTUtil();
//
//        String username = "thilanse";
//        String email = "thilansenanayake@gmail.com";
//
//        String token = jwtUtil.generateToken(username, email);
//
//        assertEquals(token, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImVtYWlsIjoidGhpbGFuc2VuYW5heWFrZUBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRoaWxhbnNlIn0._Grg-0NDX4zfJ_RbWMZ0p-Vj5ivgfuvhfUk3iOdk1VM");
//    }

    @Test
    public void test_verifyToken(){

        JWTUtil jwtUtil = new JWTUtil();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImVtYWlsIjoidGhpbGFuc2VuYW5heWFrZUBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRoaWxhbnNlIn0._Grg-0NDX4zfJ_RbWMZ0p-Vj5ivgfuvhfUk3iOdk1VM";
        String username = "thilanse";
        String email = "thilansenanayake@gmail.com";

        boolean tokenIsValid = jwtUtil.verifyToken(token, username, email);

        assertTrue(tokenIsValid);
    }

    @Test
    public void test_extractUsernameFromToken(){

        JWTUtil jwtUtil = new JWTUtil();

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImVtYWlsIjoidGhpbGFuc2VuYW5heWFrZUBnbWFpbC5jb20iLCJ1c2VybmFtZSI6InRoaWxhbnNlIn0._Grg-0NDX4zfJ_RbWMZ0p-Vj5ivgfuvhfUk3iOdk1VM";

        String username = jwtUtil.extractUsernameFromToken(token);

        assertEquals(username, "thilanse");
    }
}
