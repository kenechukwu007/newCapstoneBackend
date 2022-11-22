package com.ecommerce.akatsukiresources.dto;

import java.util.List;

public class SCartDto {
   private List<ItemDto> itemsDtoList;
    private double totalPrice;

    public SCartDto(){

    }

    public List<ItemDto> getItemsDtoList() {
        return itemsDtoList;
    }

    public void setItemsDtoList(List<ItemDto> itemsDtoList) {
        this.itemsDtoList = itemsDtoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
