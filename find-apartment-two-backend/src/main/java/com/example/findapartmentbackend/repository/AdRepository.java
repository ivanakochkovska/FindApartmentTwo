package com.example.findapartmentbackend.repository;

import com.example.findapartmentbackend.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for an ad.
 */
@Repository
public interface AdRepository extends JpaRepository<Ad,Long> {
}
