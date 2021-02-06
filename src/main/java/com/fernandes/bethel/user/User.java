package com.fernandes.bethel.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernandes.bethel.society.Society;

import javax.persistence.*;


@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)  //This is to avoid addind id manually, so the db has to do it //CREATES create table hibernate_sequence (next_val bigint) engine=InnoDB and also insert into hibernate_sequence values ( 1 ) twice
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String password;

    private String roles;

    private String userProfileImageLink;

    private String userSocietyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "society_fk", referencedColumnName = "id")
    private Society society;


    public Society getSociety() {
        return society;
    }

    public void setSociety(Society society) {
        this.society = society;
    }
//    @Enumerated(EnumType.STRING)
//    @Column(name = "auth_provider")
//    private AuthenticationProvider authenticationProvider;

    public User() {
    }

    public User(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    public User(String firstName, String lastName, String username, String password, String roles, String userSocietyName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userSocietyName = userSocietyName;

    }

    public User(String firstName, String lastName, String username, String password, String roles, String userProfileImageLink, String userSocietyName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.userProfileImageLink = userProfileImageLink;
        this.userSocietyName = userSocietyName;
    }


    public void setId(Long id) {this.id = id;}
    public Long getId() {
        return id;
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
    public String getUserProfileImageLink() {
        return userProfileImageLink;
    }

    public void setUserProfileImageLink(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    public String getUserSocietyName() {
        return userSocietyName;
    }

    public void setUserSocietyName(String userSocietyName) {
        this.userSocietyName = userSocietyName;
    }

//    public Society getUserSociety() {
//        return society;
//    }
//
//    public void setUserSociety(Society society) {
//        this.society = society;
//    }

    //
//    public AuthenticationProvider getAuthenticationProvider() {
//        return authenticationProvider;
//    }
//
//    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
//        this.authenticationProvider = authenticationProvider;
//    }
}
