package com.allinformatica.products.models;

public enum UserRolesModel {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRolesModel(String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
