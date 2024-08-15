package com.example.ims.dto;

import com.example.ims.constants.ProductStatus;

public class ProductGetDTO {
    private Integer id;
    private String name;
    private Integer quantity;
    private ProductStatus status;

    private String categoryName;
    private Integer compartimentId;

    public ProductGetDTO() {
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCompartimentId() {
        return compartimentId;
    }

    public void setCompartimentId(Integer compartimentId) {
        this.compartimentId = compartimentId;
    }
}
