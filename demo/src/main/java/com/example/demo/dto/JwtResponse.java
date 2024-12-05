package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    
    private String token ; 
    private String type = "Baerer";
    private String username ; 

    public JwtResponse(String token){
        this.token = token ; 
    }
}
