package com.example.findapartmentbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * This class represents an apartment.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor

@AllArgsConstructor
public class Apartment extends BaseEntity {
    @Column(name="municipality")
    private String municipality;
    @Column(name="size")
    private int size;
    @Column(name="number_of_bathrooms")
    private int numberOfBathrooms;
    @Column(name="number_of_bedrooms")
    private int numberOfBedrooms;
    @Column(name="parking_spot")
    private boolean parkingSpot;
    @Column(name="heating")
    @Enumerated(EnumType.STRING)
    private AdditionalInformationEnum additionalInformation;
//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "ad_id")
//    private Ad ad;
}
