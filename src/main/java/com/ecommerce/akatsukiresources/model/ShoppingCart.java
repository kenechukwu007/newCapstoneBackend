package com.ecommerce.akatsukiresources.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @JsonIgnore
    @OneToOne(targetEntity = Appuser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Appuser user;

    private int volume;

    public ShoppingCart(){

    }

    public ShoppingCart(Product product, Appuser user, int volume) {
        this.dateCreated = new Date();
        this.product = product;
        this.user = user;
        this.volume = volume;
    }



    public Integer getId() {
        return id;
    }

    // Getter and setters

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
