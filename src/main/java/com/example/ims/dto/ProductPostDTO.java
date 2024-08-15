package com.example.ims.dto;

import com.example.ims.constants.ProductStatus;
import jakarta.validation.constraints.NotNull;

public class ProductPostDTO {

    @NotNull
    private String name;
    @NotNull
    private Integer quantity;

    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer compartmentId;

    public ProductPostDTO() {
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

    public @NotNull Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotNull Integer getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(@NotNull Integer compartmentId) {
        this.compartmentId = compartmentId;
    }
}
