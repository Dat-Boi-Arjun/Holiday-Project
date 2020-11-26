package com.arjun.projects.entity;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private double maxPrice;

    private String bio;


    public User(String name, String bio, double maxPrice) {
        this.name = name;
        this.bio = bio;
        this.maxPrice = maxPrice;
    }

    public User(){}

    @Override
    public String toString() {
        return "\nUser{" +
                "id=" + this.id +
                ", name='" + name + '\'' +
                ", bio='" + this.bio + '\'' +
                ", maxPrice=" + this.maxPrice +
                '}';
    }



}
