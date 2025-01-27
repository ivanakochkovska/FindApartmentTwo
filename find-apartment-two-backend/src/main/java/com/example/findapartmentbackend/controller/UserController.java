package com.example.findapartmentbackend.controller;

import com.example.findapartmentbackend.service.AdService;
import com.example.findapartmentbackend.service.UserAdService;
import com.example.findapartmentbackend.service.UserService;
import io.tej.SwaggerCodgen.api.UserApi;
import io.tej.SwaggerCodgen.model.AdItem;
import io.tej.SwaggerCodgen.model.LoginUser;
import io.tej.SwaggerCodgen.model.UserAd;
import io.tej.SwaggerCodgen.model.UserItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * This class reprents a controller for user methods.
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;
    private final UserAdService userAdService;
    private final AdService adService;
    private final ObjectFactory<HttpSession> httpSessionFactory;

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
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ResponseEntity<Void> test() {

        return ResponseEntity.ok().build();
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Void> logout() {
        HttpSession httpSession = httpSessionFactory.getObject();
        httpSession.removeAttribute("username");
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<AdItem>> userAdsGet() {
        return ResponseEntity.ok(adService.getAds());
    }

    @RequestMapping(value="/logged", method = RequestMethod.GET)
    public ResponseEntity<String> ifLoggedUser() {
        HttpSession session = httpSessionFactory.getObject();
        String username = (String) session.getAttribute("username");
        return ResponseEntity.ok(username);
    }

    @Override
    public ResponseEntity<List<AdItem>> userBookmarkedAdsGet() {
        return ResponseEntity.ok(userAdService.getBookmarkedAds());
    }
}
