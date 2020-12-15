package com.example.findapartmentbackend.controller;

import com.example.findapartmentbackend.service.AdService;
import com.example.findapartmentbackend.service.UserAdService;
import com.example.findapartmentbackend.service.UserService;
import io.tej.SwaggerCodgen.api.UserApi;
import io.tej.SwaggerCodgen.model.LoginUser;
import io.tej.SwaggerCodgen.model.UserAd;
import io.tej.SwaggerCodgen.model.UserItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


/**
 * This class reprents a controller for user methods.
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final UserAdService userAdService;
    private final AdService adService;
    @Autowired
    ObjectFactory<HttpSession> httpSessionFactory;

    @Override
    public ResponseEntity<Void> saveUser(UserItem userItem) {
        userService.saveUser(userItem.getName(),userItem.getPassword(),userItem.getSurname(),userItem.getUsername());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> loginUser(LoginUser loginUser) {

        userService.loginUser(loginUser.getUsername(),loginUser.getPassword());
        HttpSession httpSession = httpSessionFactory.getObject();
        httpSession.setAttribute("username", loginUser.getUsername());
        System.out.println(httpSession.getAttribute("username"));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> bookmarkAds(UserAd userAd) {
        HttpSession httpSession = httpSessionFactory.getObject();
        String username = (String) httpSession.getAttribute("username");
        Long adId = Long.parseLong(userAd.getAdUserId());
        userService.saveBookmark(adId,username);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteBookmarkAd(String adUserId) {
        userAdService.deleteAd(adUserId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteAd(String adUserId) {
        adService.deleteAd(adUserId);
        return ResponseEntity.ok().build();
    }
}
