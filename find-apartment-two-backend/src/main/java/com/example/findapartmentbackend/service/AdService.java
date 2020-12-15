package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.model.AdditionalInformationEnum;
import org.springframework.stereotype.Service;

/**
 * This interface represents a service for Ad.
 */
@Service
public interface AdService {
    void saveAd(String username,boolean status, float price, String name, String surname, String email, String phoneNumber, String description,
                String municipality, int size, int numberOfBathrooms, int numberOfBedrooms, boolean parkingSpot, AdditionalInformationEnum additionalInformationEnum);
    void deleteAd(String adId);
}
