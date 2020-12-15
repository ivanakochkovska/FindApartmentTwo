package com.example.findapartmentbackend.controller;

import com.example.findapartmentbackend.Exceptions.ErrorCode;
import com.example.findapartmentbackend.Exceptions.FindApartmentBusinessException;
import com.example.findapartmentbackend.mapper.AdditionaInformationMapper;
import com.example.findapartmentbackend.service.AdService;
import io.tej.SwaggerCodgen.api.AdApi;
import io.tej.SwaggerCodgen.model.AdItem;
import io.tej.SwaggerCodgen.model.AdRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class AdController implements AdApi {
    private final ObjectFactory<HttpSession> httpSessionFactory;
    private final AdService adService;
    private final AdditionaInformationMapper additionaInformationMapper;

    @Override
    public ResponseEntity<AdItem> getAds(AdRequest adRequest) {
        //adService.searchForAds(adRequest.getMunicipality(),adRequest.s)
        return null;
    }

    private boolean ifLogin(String username) {
        if(username!=null) {
        HttpSession httpSession = httpSessionFactory.getObject();
        String usernameFromSession = (String) httpSession.getAttribute("username");
        return usernameFromSession.equals(username); }
        return false;
    }
    @Override
    public ResponseEntity<Void> saveAd(AdItem adItem) {
        HttpSession httpSession = httpSessionFactory.getObject();
        String username = (String) httpSession.getAttribute("username");
        if(!ifLogin(username)) {
            throw new FindApartmentBusinessException(ErrorCode.NOT_LOGGED_IN,"Please, log in to post an ad.");
        }
        if (adItem.getPhoneNumber() != null || adItem.getEmail() != null) {

            adService.saveAd(username,adItem.getStatus(), adItem.getPrice(), adItem.getName(), adItem.getSurname(),
                    adItem.getEmail(), adItem.getPhoneNumber(), adItem.getDescription(),
                    adItem.getApartment().getMunicipality(), adItem.getApartment().getSize(),
                    adItem.getApartment().getNumberOfBathrooms(), adItem.getApartment().getNumberOfBedrooms(),
                    adItem.getApartment().getParkingSpot(),
                    additionaInformationMapper.mapAdditionalInformation(adItem.getApartment().getAdditionalInformation()));
        } else {
            throw new FindApartmentBusinessException(ErrorCode.PROVIDE_PERSONAL_INFORMATION, "Please provide contact information. ");
        }
        return ResponseEntity.ok().build();
    }
}
