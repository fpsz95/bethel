package com.fernandes.bethel.society;

import com.fernandes.bethel.user.User;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "Societies")
@Table(name =  "Societies", uniqueConstraints = @UniqueConstraint(columnNames = "societyEmailId"))
public class Society {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;  //it is id only instead of societyId in the database
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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;
//
//    public User getUser() {return user;}
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Society() {
    }

    public Society(Long id,
                   Long societyRegistrationId,
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
        this.id = id;
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

    public Long getId() { return id; }

    public Long getSocietyRegistrationId() {
        return societyRegistrationId;
    }

    public String getSocietyName() {
        return societyName;
    }

    public String getWingName() {
        return wingName;
    }

    public String getWard() {
        return ward;
    }

    public Long getPlotNo() {
        return plotNo;
    }

    public String getArea() {
        return area;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public String getSocietyEmailId() {
        return societyEmailId;
    }

}
