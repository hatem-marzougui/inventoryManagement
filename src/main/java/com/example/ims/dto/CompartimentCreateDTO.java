package com.example.ims.dto;

import jakarta.validation.constraints.NotNull;

public class CompartimentCreateDTO {

    @NotNull
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer availablePlace;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAvailablePlace() {
        return availablePlace;
    }

    public void setAvailablePlace(Integer availablePlace) {
        this.availablePlace = availablePlace;
    }
}
