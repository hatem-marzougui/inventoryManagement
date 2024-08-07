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

    private Integer availablePlace;

    @OneToMany(mappedBy = "compartiment")
    private List<Product> products;
}
