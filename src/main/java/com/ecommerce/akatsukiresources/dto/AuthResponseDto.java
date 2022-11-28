package com.ecommerce.akatsukiresources.dto;

public class AuthResponseDto extends ResponseDto{

    private String token;

    public AuthResponseDto(Integer status, String message, String token) {
        super(status, message);
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
