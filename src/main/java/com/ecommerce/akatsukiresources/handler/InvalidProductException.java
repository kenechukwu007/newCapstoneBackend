package com.ecommerce.akatsukiresources.handler;

public class InvalidProductException  extends IllegalArgumentException{

    public InvalidProductException(String message){
        super(message);
    }
}
