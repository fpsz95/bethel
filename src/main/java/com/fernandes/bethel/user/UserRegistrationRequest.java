package com.fernandes.bethel.user;

import com.fernandes.bethel.society.Society;

import java.util.List;

public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;
    private Society society;

    public UserRegistrationRequest(String firstName,
                                   String lastName,
                                   String username,
                                   String password,
                                   String roles,
                                   Society society) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.society = society;
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public Society getSociety() {
        return society;
    }
    public void setSociety(Society society) {
        this.society = society;
    }

    @Override
    public String toString() {
        return "UserRegistrationRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", society=" + society +
                '}';
    }
}
