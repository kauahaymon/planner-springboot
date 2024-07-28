package com.rocketseat.planner.link;


import com.rocketseat.planner.trip.Trip;
import com.rocketseat.planner.trip.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}