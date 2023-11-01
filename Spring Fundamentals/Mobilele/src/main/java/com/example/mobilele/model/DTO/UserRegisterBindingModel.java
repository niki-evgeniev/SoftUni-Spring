package com.example.mobilele.model.DTO;

import com.example.mobilele.model.enums.RolesType;
import jakarta.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @Size(min = 3, max = 20, message = "Firstname length must be between 3 and 20 characters!")
    private String firstName;

    @Size(min = 3, max = 20, message = "Lastname length must be between 3 and 20 characters!")
    private String lastName;

    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String confirmPassword;

    private RolesType role;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesType getRole() {
        return role;
    }

    public void setRole(RolesType role) {
        this.role = role;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
