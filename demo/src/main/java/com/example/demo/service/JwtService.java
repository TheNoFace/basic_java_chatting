package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.security.JwtTokenProvider;

@Service
public class JwtService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String generateToken(Authentication authentication) {
        return jwtTokenProvider.generateToken(authentication);
    }

    public String getUsernameFromToken(String token) {
        return jwtTokenProvider.getUserNameFromJwtToken(token);
    }

    public boolean validateToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
