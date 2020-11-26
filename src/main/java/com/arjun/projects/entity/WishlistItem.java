package com.arjun.projects.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class WishlistItem {

    @Id
    private String user;

    private String name;
    private double price;

    public WishlistItem() {}

    public WishlistItem(String user, String name, double price) {
        this.user = user;
        this.name = name;
        this.price = price;
    }
}
