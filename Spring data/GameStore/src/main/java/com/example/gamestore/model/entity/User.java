package com.example.gamestore.model.entity;

import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @ManyToMany
    private Set<Game> games;
    @Column(name = "is_admin")
    private Boolean isAdmin;


    public User() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
