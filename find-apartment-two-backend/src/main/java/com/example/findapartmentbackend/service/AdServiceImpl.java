package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.mapper.AdMapper;
import com.example.findapartmentbackend.model.Ad;
import com.example.findapartmentbackend.model.AdditionalInformationEnum;
import com.example.findapartmentbackend.model.Apartment;
import com.example.findapartmentbackend.model.User;
import com.example.findapartmentbackend.repository.AdRepository;
import com.example.findapartmentbackend.repository.ApartmentRepository;
import com.example.findapartmentbackend.repository.UserRepository;
import io.tej.SwaggerCodgen.model.AdItem;
import io.tej.SwaggerCodgen.model.AdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents an implementation of AdService interface.
 */
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final ObjectFactory<HttpSession> httpSessionFactory;
    private final AdRepository adRepository;
    private final ApartmentRepository apartmentRepository;
    private final UserService userService;
    private final AdMapper adMapper;

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
        Apartment apartment = new Apartment(municipality, size, numberOfBathrooms, numberOfBedrooms, parkingSpot,
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

    private boolean checkUserInAdd(User user) {
        return adRepository.findAll().
                stream().
                anyMatch(ad -> ad.getUser().getUsername().equals(user.getUsername()));

    }

    @Override
    public List<AdItem> getAds() {
        HttpSession session = httpSessionFactory.getObject();
        String username = (String) session.getAttribute("username");
        User user = userService.findUserByUsername(username).get();
        List<Ad> adList = adRepository.findAll();
        List<Ad> filteredAds = adList.stream().
                filter(ad -> checkUserInAdd(user)).
                collect(Collectors.toList());
        List<AdItem> adItems = filteredAds.stream().map(adMapper::adToAddItem).collect(Collectors.toList());
        return adItems;
    }


    @Override
    public List<AdItem> searchForAds(AdRequest adRequest) {
        List<Ad> adList = adRepository.findAll();
        String municipalityPresence = adRequest.getMunicipality();
        Integer numBathrooms = adRequest.getNumberOfBathrooms();
        Integer numBedrooms = adRequest.getNumberOfBedrooms();
        Boolean parkingSpot = adRequest.getParkingSpot();
        Integer priceFrom = adRequest.getPriceFrom();
        Integer priceTo = adRequest.getPriceTo();
        Integer sizeFrom = adRequest.getSizeStart();
        Integer sizeTo = adRequest.getSizeEnd();
        List<Ad> adListOne = adList.stream()
                .filter(ad -> {
                    if(municipalityPresence!=null){
                        return ad.getApartment().getMunicipality().equals(municipalityPresence);
                    } else {
                        return true;
                    }
                }).filter(ad -> {
                    if(numBathrooms!=null) {
                        return (new Integer(ad.getApartment().getNumberOfBedrooms()).equals(numBathrooms));
                    } else {
                        return true;
                    }
                }).filter(ad->{
                    if(numBedrooms!=null) {
                        return (new Integer(ad.getApartment().getNumberOfBedrooms()).equals(numBathrooms));
                    } else {
                        return true;
                    }
                }).filter(ad->{
                    if(parkingSpot!=null) {
                        return (new Boolean(ad.getApartment().isParkingSpot())).equals(parkingSpot);
                    } else {
                        return true;
                    }
                }).filter(ad->{
                    if(priceFrom!=null && priceTo!=null) {
                        int res = new Integer((int)ad.getPrice()).compareTo(priceFrom);
                        int res1 = new Integer((int) ad.getPrice()).compareTo(priceTo);
                            return res >=0 && res1<=0;
                    } else {
                        return true;
                    }
                }).filter(ad-> {
                    if (sizeFrom != null && sizeTo != null) {
                        int res = new Integer((int) ad.getApartment().getSize()).compareTo(sizeFrom);
                        int res1 = new Integer((int) ad.getApartment().getSize()).compareTo(sizeTo);
                        return res >= 0 && res1 <= 0;
                    } else {
                        return true;
                    }
                }).collect(Collectors.toList());


        return adListOne.stream().map(adMapper::adToAddItem).collect(Collectors.toList());
    }
}


