package com.ecommerce.akatsukiresources.dto;

import javax.validation.constraints.NotNull;

public class AddItemDto {

    private Integer id;
    private @NotNull Integer productid;
    private @NotNull Integer volume;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
