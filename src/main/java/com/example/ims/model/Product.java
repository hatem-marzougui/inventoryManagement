package com.example.ims.model;

import com.example.ims.constants.ProductStatus;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "compartimentId", referencedColumnName = "id")
    private Compartiment compartiment;

    private ProductStatus status;

}
