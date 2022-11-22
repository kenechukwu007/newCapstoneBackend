package com.ecommerce.akatsukiresources.dto;

public class LoginDtoReceipt {

    private String status;
    private String response;

    public String getStatus() {
        return status;
    }

    public LoginDtoReceipt(String status, String response) {
        this.status = status;
        this.response = response;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
