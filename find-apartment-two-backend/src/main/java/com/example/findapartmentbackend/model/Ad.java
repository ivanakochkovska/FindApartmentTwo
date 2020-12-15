package com.example.findapartmentbackend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * This class represents an ad.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor

public class Ad extends BaseEntity {
    @Column(name = "published_on")
    private LocalDate publishedOn;
    @Column(name = "status")
    private boolean status;
    @Column(name = "price")
    private float price;
//    @OneToOne(fetch = FetchType.LAZY,
//            cascade =  CascadeType.ALL,
//            mappedBy = "ad")
    //publisher information
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "description")
    private String description;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<UserAd> a;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private User user;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;
}
