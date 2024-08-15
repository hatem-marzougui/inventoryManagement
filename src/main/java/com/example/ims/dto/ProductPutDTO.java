package com.example.ims.dto;

import com.example.ims.constants.ProductStatus;
import jakarta.validation.constraints.NotNull;

public class ProductPutDTO {
    private Integer id;
    private String name;

    private Integer quantity;

    private String categoryName;
    private Integer compartmentId;

    public ProductPutDTO() {
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



    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(Integer compartmentId) {
        this.compartmentId = compartmentId;
    }
}
