package com.fernandes.bethel.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fernandes.bethel.society.Society;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Random;
import java.util.UUID;

@Component
@Entity
//@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String password;

    private String roles;

    private String userProfileImageLink;

    private Long serviceCharges;

    private Long waterCharges;

    private Long totalCharges;

    private Long orderId;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "society_id", referencedColumnName = "id")
    private Society society;

    public User() {
    }

    public User(Long serviceCharges,
                Long waterCharges,
                Long totalCharges,
                UUID orderId){
    }

    public User(String userProfileImageLink) {
        this.userProfileImageLink = userProfileImageLink;
    }

    public User(String firstName,
                String lastName,
                String username,
                String password,
                String roles,
                Society society) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.society = society;
    }

    public User(String firstName,
                String lastName,
                String username,
                String password,
                String roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setId(Long id) {this.id = id;}
    public Long getId() { return id; }
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
    public void setUserProfileImageLink(String userProfileImageLink) { this.userProfileImageLink = userProfileImageLink; }

    @JsonSerialize
    @JsonBackReference
    public Society getSociety() {
        return society;
    }
    public void setSociety(Society society) {
        this.society = society;
    }

    public Long getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Long serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public Long getWaterCharges() {
        return waterCharges;
    }

    public void setWaterCharges(Long waterCharges) {
        this.waterCharges = waterCharges;
    }

    public Long getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(Long totalCharges) {
        this.totalCharges = totalCharges;
    }

    public Long getOrderId() {
        return  1L;//Math.abs(new Random().nextLong());
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
