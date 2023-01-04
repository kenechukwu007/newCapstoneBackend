package com.ecommerce.akatsukiresources.dto;

import com.ecommerce.akatsukiresources.enums.Role;

public class ResponseDto {

    private Integer status;
    private String message;



//    public ResponseDto(Integer status, String message) {
//        this.status = status;
//        this.message = message;
//
//    }


    public ResponseDto(Integer status, String message) {
        this.status = status;
        this.message = message;

    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
