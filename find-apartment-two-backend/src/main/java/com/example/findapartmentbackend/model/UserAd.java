package com.example.findapartmentbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class UserAd extends BaseEntity {

    @ManyToOne
    private Ad ad;
    @ManyToOne
    private User user;
}
