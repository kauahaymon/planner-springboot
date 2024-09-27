package com.rocketseat.planner.services;


import com.rocketseat.planner.entities.link.Link;
import com.rocketseat.planner.entities.link.LinkData;
import com.rocketseat.planner.entities.link.LinkRequestPayload;
import com.rocketseat.planner.entities.link.LinkResponse;
import com.rocketseat.planner.repositories.LinkRepository;
import com.rocketseat.planner.entities.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository repository;

    @Autowired
    private TripService tripService;

    // Create New Link
    public LinkResponse registerLink(UUID id, LinkRequestPayload payload) {
        Optional<Trip> optionalTrip = this.tripService.getTripDetails(id);
        if (optionalTrip.isPresent()) {
            Trip rawTrip = optionalTrip.get();

            Link newLink = new Link(payload.title(), payload.url(), rawTrip);
            this.repository.save(newLink);

            return new LinkResponse(newLink.getId());
        }
        return null;
    }

    // Retrieve Links
    public List<LinkData> getAllLinksFromTrip(UUID tripId) {
        return this.repository
                .findByTripId(tripId)
                .stream()
                .map(link -> new LinkData(
                        link.id(),
                        link.title(),
                        link.url()))
                .toList();
    }
}