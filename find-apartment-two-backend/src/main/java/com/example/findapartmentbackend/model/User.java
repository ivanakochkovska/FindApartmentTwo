package com.example.findapartmentbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents a user.
 */
@Entity
@Getter
@Setter
@RequiredArgsConstructor

public class User extends BaseEntity {
    private String firstName;
    private String surname;
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<UserAd> a;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
