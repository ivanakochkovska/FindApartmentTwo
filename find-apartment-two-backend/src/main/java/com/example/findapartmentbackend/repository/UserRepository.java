package com.example.findapartmentbackend.repository;

import com.example.findapartmentbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for a user.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
