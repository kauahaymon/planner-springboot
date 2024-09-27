package com.rocketseat.planner.repositories;

import com.rocketseat.planner.entities.link.Link;
import com.rocketseat.planner.entities.link.LinkData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<Link, UUID> {
    public List<LinkData> findByTripId(UUID id);
}
