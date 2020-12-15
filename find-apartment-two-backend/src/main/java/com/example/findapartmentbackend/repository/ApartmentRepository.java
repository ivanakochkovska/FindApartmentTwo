package com.example.findapartmentbackend.repository;

import com.example.findapartmentbackend.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for an apartment.
 */
@Repository
public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
}
