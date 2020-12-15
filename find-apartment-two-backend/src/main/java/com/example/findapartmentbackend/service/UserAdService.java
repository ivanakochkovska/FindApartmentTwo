package com.example.findapartmentbackend.service;

import org.springframework.stereotype.Service;

@Service
public interface UserAdService {
    void deleteAd(String adUserId);

}
