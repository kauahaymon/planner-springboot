package com.rocketseat.planner.repositories;

import com.rocketseat.planner.entities.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}
