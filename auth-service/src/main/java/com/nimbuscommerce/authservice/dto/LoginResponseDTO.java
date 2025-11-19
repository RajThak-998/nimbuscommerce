package com.nimbuscommerce.authservice.dto;

public class LoginResponseDTO {
    public final String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
