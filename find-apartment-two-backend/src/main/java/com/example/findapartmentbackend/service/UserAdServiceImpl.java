package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.model.UserAd;
import com.example.findapartmentbackend.repository.UserAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdServiceImpl implements UserAdService {
    private final UserAdRepository userAdRepository;

    @Override
    public void deleteAd(String adUserId) {
        Long adIdLong = Long.parseLong(adUserId);
        Optional<UserAd> userAd = userAdRepository.findById(adIdLong);
        userAd.ifPresent(userAdRepository::delete);
    }

}
