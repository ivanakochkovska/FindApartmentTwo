package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.model.Ad;
import com.example.findapartmentbackend.model.AdditionalInformationEnum;
import com.example.findapartmentbackend.model.Apartment;
import com.example.findapartmentbackend.model.User;
import com.example.findapartmentbackend.repository.AdRepository;
import com.example.findapartmentbackend.repository.ApartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * This class represents an implementation of AdService interface.
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private  final AdRepository adRepository;
    private final ApartmentRepository apartmentRepository;
    private final UserService userService;
    @Override
    public void saveAd(String username, boolean status, float price, String name, String surname, String email, String phoneNumber, String description,
                       String municipality, int size, int numberOfBathrooms, int numberOfBedrooms, boolean parkingSpot, AdditionalInformationEnum additionalInformationEnum) {
       LocalDate getCurrentDate = LocalDate.now();
        Optional<User> user = userService.findUserByUsername(username);
        Ad ad = new Ad();
        ad.setStatus(status);
        ad.setPrice(price);
        ad.setName(name);
        ad.setSurname(surname);
        ad.setEmail(email);
        ad.setPhoneNumber(phoneNumber);
        ad.setDescription(description);
        ad.setPublishedOn(getCurrentDate);
        ad.setUser(user.get());
        Apartment apartment = new Apartment(municipality, size, numberOfBathrooms, numberOfBedrooms,parkingSpot,
                additionalInformationEnum);
        ad.setApartment(apartment);
        adRepository.save(ad);
        apartmentRepository.save(apartment);
    }

    @Override
    public void deleteAd(String adId) {
        List<Ad> adList = adRepository.findAll();
        Long id = Long.parseLong(adId);
        Ad ad = adRepository.findById(id).get();
        adRepository.delete(ad);
    }
}


