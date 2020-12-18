package com.example.findapartmentbackend.service;

import io.tej.SwaggerCodgen.model.AdItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserAdService {
    void deleteAd(String adUserId);
    List<AdItem> getBookmarkedAds();

}
