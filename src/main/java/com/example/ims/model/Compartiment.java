package com.example.ims.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Compartiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer capacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAvailablePlace() {
        return availablePlace;
    }

    public void setAvailablePlace(Integer availablePlace) {
        this.availablePlace = availablePlace;
    }

    private Integer availablePlace;

    @OneToMany(mappedBy = "compartiment")
    private List<Product> products;
}
