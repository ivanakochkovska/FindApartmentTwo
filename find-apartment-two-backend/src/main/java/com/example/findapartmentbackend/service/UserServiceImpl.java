package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.Exceptions.ErrorCode;
import com.example.findapartmentbackend.Exceptions.FindApartmentBusinessException;
import com.example.findapartmentbackend.model.Ad;
import com.example.findapartmentbackend.model.User;
import com.example.findapartmentbackend.model.UserAd;
import com.example.findapartmentbackend.repository.AdRepository;
import com.example.findapartmentbackend.repository.UserAdRepository;
import com.example.findapartmentbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * This class represents an implementation of the UserService.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    private final AdRepository adRepository;
    private final UserAdRepository userAdRepository;
    @Override
    public void saveUser(String name, String password, String surname, String username) {
        if(checkUsername(username)) {
            throw new FindApartmentBusinessException(ErrorCode.USER_ALREADY_EXISTS,"Username already exists.");
        }
        User user = new User();
        user.setFirstName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setUsername(username);
        userRepository.save(user);
    }

    private boolean checkUsername(String username) {
        List<User> users = userRepository.findAll();
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        List<User> users = userRepository.findAll();
        Optional<User> userFound = users.stream()
                .filter(user -> user.getUsername().equals(username)&&user.getPassword().equals(password))
                .findFirst();
        if(userFound.isPresent()) {
            return userFound;
        } else {
            throw new FindApartmentBusinessException(ErrorCode.USER_NOT_FOUND,"There is no user with provided credentials. ");
        }
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void saveBookmark(Long id, String username) {
        Ad ad = adRepository.findById(id).get();
        System.out.println(username);
        User user = findUserByUsername(username).get();
        UserAd userAd = new UserAd();
        userAd.setAd(ad);
        userAd.setUser(user);
        userAdRepository.save(userAd);
    }
}
