package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {
    
    @NotBlank
    @Size(min = 3 , max = 20 )
    private String username ; 

    @NotBlank
    @Size(min = 6 , max = 20 )
    private String password ; 

}
