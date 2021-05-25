package com.fernandes.bethel.society;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fernandes.bethel.user.User;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Optional;

@Component
@Entity(name = "Societies")
@Table(name =  "Societies", uniqueConstraints = @UniqueConstraint(columnNames = "societyEmailId"))
public class Society {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private Long societyRegistrationId;
    private String societyName;
    private String wingName;
    private String ward;
    private Long plotNo;
    private String area;
    private String street;
    private String city;
    private Long pinCode;
    private Long phoneNo;
    @NotNull
    private String societyEmailId;


    //@JsonIgnoreProperties("user")
    //@OneToOne(mappedBy = "society", cascade = CascadeType.ALL)

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "society")
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    public Society() {
    }

    public Society(Long societyRegistrationId,
                   String societyName,
                   String wingName,
                   String ward,
                   Long plotNo,
                   String area,
                   String street,
                   String city,
                   Long pinCode,
                   Long phoneNo,
                   String societyEmailId) {
        this.societyRegistrationId = societyRegistrationId;
        this.societyName = societyName;
        this.wingName = wingName;
        this.ward = ward;
        this.plotNo = plotNo;
        this.area = area;
        this.street = street;
        this.city = city;
        this.pinCode = pinCode;
        this.phoneNo = phoneNo;
        this.societyEmailId = societyEmailId;
    }

    public Long getSocietyId() {
        return id;
    }
    public void setSocietyId(Long societyId) {
        this.id = societyId;
    }
    public Long getSocietyRegistrationId() {
        return societyRegistrationId;
    }
    public void setSocietyRegistrationId(Long societyRegistrationId) {
        this.societyRegistrationId = societyRegistrationId;
    }
    public String getSocietyName() {
        return societyName;
    }
    public void setSocietyName(String societyName) {
        this.societyName = societyName;
    }
    public String getWingName() {
        return wingName;
    }
    public void setWingName(String wingName) {
        this.wingName = wingName;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public Long getPlotNo() {
        return plotNo;
    }
    public void setPlotNo(Long plotNo) {
        this.plotNo = plotNo;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Long getPinCode() {
        return pinCode;
    }
    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }
    public Long getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getSocietyEmailId() {
        return societyEmailId;
    }
    public void setSocietyEmailId(String societyEmailId) {
        this.societyEmailId = societyEmailId;
    }


//    @JsonManagedReference
//    //@JsonSerialize
//    public User getUser() {
//        return user;
//    }
//    public void setUser(User user) {
//        this.user = user;
//    }
}
