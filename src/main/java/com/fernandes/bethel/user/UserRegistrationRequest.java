package com.fernandes.bethel.user;

public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String roles;
    private String userSocietyName;

    public UserRegistrationRequest(String firstName,
                                   String lastName,
                                   String username,
                                   String password,
                                   String roles,
                                   String userSocietyName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userSocietyName = userSocietyName;
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


    public String getUserSocietyName() {
        return userSocietyName;
    }

    public void setUserSocietyName(String userSocietyName) {
        this.userSocietyName = userSocietyName;
    }
}
