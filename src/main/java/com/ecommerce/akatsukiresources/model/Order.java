package com.ecommerce.akatsukiresources.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "session_id")
    private String sessionVal;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Appuser user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<PlaceOrder> placeOrders;

    public Order() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSessionVal() {
        return sessionVal;
    }

    public void setSessionVal(String sessionVal) {
        this.sessionVal = sessionVal;
    }

    public Appuser getUser() {
        return user;
    }

    public void setUser(Appuser user) {
        this.user = user;
    }

    public List<PlaceOrder> getPlaceOrders() {
        return placeOrders;
    }

    public void setPlaceOrders(List<PlaceOrder> placeOrders) {
        this.placeOrders = placeOrders;
    }
}
