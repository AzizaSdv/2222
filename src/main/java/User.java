package com.example.tourmanagement;

public class User {
    protected int id;
    protected String name;
    protected String email;
    protected String password;
    protected String role; // "admin" или "customer"

    public User(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
