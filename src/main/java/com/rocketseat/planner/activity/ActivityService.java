package com.rocketseat.planner.activity;

import com.rocketseat.planner.trip.Trip;
import com.rocketseat.planner.trip.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private TripRepository tripRepository;

    // Register New Activity
    public ActivityResponse registerActivity(UUID id, ActivityRequestPayload payload) {
        Optional<Trip> optionalTrip = this.tripRepository.findById(id);
        if(optionalTrip.isPresent()) {
            Trip rawTrip = optionalTrip.get();
            Activity newActivity = new Activity(payload.title(), payload.occurs_at(), rawTrip);
            this.repository.save(newActivity);

            return new ActivityResponse(newActivity.getId());
        }

        return null;
    }
}
