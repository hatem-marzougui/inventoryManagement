package com.example.ims.dto;


import jakarta.validation.constraints.NotNull;

public class EmployeeGetDTO {

    @NotNull
    private Integer id;

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String address;


    public EmployeeGetDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
