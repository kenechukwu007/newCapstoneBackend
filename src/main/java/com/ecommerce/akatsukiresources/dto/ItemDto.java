package com.ecommerce.akatsukiresources.dto;

import com.ecommerce.akatsukiresources.model.Product;
import com.ecommerce.akatsukiresources.model.ShoppingCart;

public class ItemDto {

    private Integer id;
    private Integer volume;
    private Product product1;

    public ItemDto(ShoppingCart shoppingCart){
        this.id = shoppingCart.getId();
        this.volume = shoppingCart.getVolume();
        this.setProduct1(shoppingCart.getProduct());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Product getProduct1() {
        return product1;
    }

    public void setProduct1(Product product1) {
        this.product1 = product1;
    }

    public ItemDto() {
    }
}
