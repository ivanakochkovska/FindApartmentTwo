package com.example.findapartmentbackend.service;

import com.example.findapartmentbackend.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This interface represents a service for user.
 */
@Service
public interface UserService {
    void saveUser(String name, String password, String surname, String username);
    Optional<User> loginUser(String username, String password);
    void saveBookmark(Long id, String username);
    public Optional<User> findUserByUsername(String username);

}
