package com.example.findapartmentbackend.repository;

import com.example.findapartmentbackend.model.UserAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdRepository extends JpaRepository<UserAd,Long> {
}
