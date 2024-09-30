package travelagency.travelagency.repositories;

import travelagency.travelagency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    // Additional query methods can be defined here if needed
}