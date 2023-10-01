package com.example.mobilele.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    private RolesType role;

    public UserRole() {
    }

    public RolesType getRole() {
        return role;
    }

    public void setRole(RolesType role) {
        this.role = role;
    }
}
