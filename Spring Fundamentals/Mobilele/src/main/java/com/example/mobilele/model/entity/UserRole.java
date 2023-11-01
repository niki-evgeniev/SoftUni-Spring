package com.example.mobilele.model.entity;


import com.example.mobilele.model.enums.RolesType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RolesType roles;

    public UserRole() {
    }

    public RolesType getRoles() {
        return roles;
    }

    public void setRoles(RolesType roles) {
        this.roles = roles;
    }
}
