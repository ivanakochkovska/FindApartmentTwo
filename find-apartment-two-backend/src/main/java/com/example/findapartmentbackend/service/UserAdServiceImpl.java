package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.mapper.AdMapper;
import com.example.findapartmentbackend.model.Ad;
import com.example.findapartmentbackend.model.User;
import com.example.findapartmentbackend.model.UserAd;
import com.example.findapartmentbackend.repository.UserAdRepository;
import io.tej.SwaggerCodgen.model.AdItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAdServiceImpl implements UserAdService {
    private final UserAdRepository userAdRepository;
    private final AdMapper adMapper;
    private final ObjectFactory<HttpSession> httpSessionFactory;
    private final UserService userService;

    @Override
    public void deleteAd(String adUserId) {
        Long adIdLong = Long.parseLong(adUserId);
        Optional<UserAd> userAd = userAdRepository.findById(adIdLong);
        userAd.ifPresent(userAdRepository::delete);
    }

    private boolean ifBookmarkedAd(User user) {
        List<UserAd> userAds = userAdRepository.findAll();
        return userAds.stream()
                .anyMatch(userAd -> userAd.getUser().getId().equals(user.getId()));


    }

    @Override
    public List<AdItem> getBookmarkedAds() {
        HttpSession session = httpSessionFactory.getObject();
        String username = (String) session.getAttribute("username");
        User user = userService.findUserByUsername(username).get();
        List<UserAd> userAds = userAdRepository.findAll();
        List<Ad> ads = userAds.stream()
                .filter(userAd -> ifBookmarkedAd(user))
                .map(UserAd::getAd)
                .collect(Collectors.toList());
        List<AdItem> adItems = ads
                .stream()
                .map(adMapper:: adToAddItem )
                .collect(Collectors.toList());

        return adItems;
    }
}
