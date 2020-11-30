package com.arjun.projects.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {

    @Id
    private String name;

    private double maxPrice;

    private String bio;
    private int score;


    public User(String name, double maxPrice) {
        this.name = name;
        this.maxPrice = maxPrice;
    }

    public User(){}

    @Override
    public String toString() {
        return "\nUser{" +
                "name='" + name + '\'' +
                ", bio='" + this.bio + '\'' +
                ", maxPrice=" + this.maxPrice +
                '}';
    }



}
