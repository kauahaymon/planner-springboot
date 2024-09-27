package com.rocketseat.planner.controllers;

import com.rocketseat.planner.entities.activity.ActivityData;
import com.rocketseat.planner.entities.activity.ActivityRequestPayload;
import com.rocketseat.planner.entities.activity.ActivityResponse;
import com.rocketseat.planner.entities.trip.Trip;
import com.rocketseat.planner.entities.trip.TripRequestPayload;
import com.rocketseat.planner.entities.trip.TripResponse;
import com.rocketseat.planner.services.ActivityService;
import com.rocketseat.planner.exception.ResourceNotFoundException;
import com.rocketseat.planner.entities.link.LinkData;
import com.rocketseat.planner.entities.link.LinkRequestPayload;
import com.rocketseat.planner.entities.link.LinkResponse;
import com.rocketseat.planner.services.LinkService;
import com.rocketseat.planner.entities.participant.ParticipantData;
import com.rocketseat.planner.entities.participant.ParticipantRequestPayload;
import com.rocketseat.planner.entities.participant.ParticipantResponse;
import com.rocketseat.planner.services.ParticipantService;
import com.rocketseat.planner.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    // TRIPS
    @PostMapping
    public ResponseEntity<TripResponse> createNewTrip(@RequestBody TripRequestPayload payload) {
        TripResponse tripResponse = this.tripService.createNewTrip(payload);
        return ResponseEntity.ok(tripResponse);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID tripId) {
        Optional<Trip> optionalTrip = this.tripService.getTripDetails(tripId);
        return optionalTrip
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID tripId, @RequestBody TripRequestPayload payload) {
        Optional<Trip> updatedTrip = this.tripService.updateTrip(tripId, payload);
        return updatedTrip
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    @GetMapping("/{tripId}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID tripId) {
        Optional<Trip> confirmTrip = this.tripService.confirmTrip(tripId);
        return confirmTrip
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    // PARTICIPANTS
    @PostMapping("/{tripId}/invite")
    public ResponseEntity<ParticipantResponse> inviteParticipant(@PathVariable UUID tripId, @RequestBody ParticipantRequestPayload payload) {
        ParticipantResponse participantResponse = this.participantService.inviteParticipant(tripId, payload);
        return Optional.ofNullable(participantResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    @GetMapping("/{tripId}/participants")
    public ResponseEntity<List<ParticipantData>> retrieveParticipants(@PathVariable UUID tripId) {
        List<ParticipantData> participantData = this.participantService.getAllParticipantsFromTrip(tripId);
        return ResponseEntity.ok(participantData);
    }

    // ACTIVITIES
    @PostMapping("/{tripId}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID tripId, @RequestBody ActivityRequestPayload payload) {
        ActivityResponse responseEntity = this.activityService.registerActivity(tripId, payload);
        return Optional.ofNullable(responseEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    @GetMapping("/{tripId}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID tripId) {
        List<ActivityData> activityDataList = this.activityService.getAllActivitiesFromTrip(tripId);
        return ResponseEntity.ok(activityDataList);
    }

    // LINKS
    @PostMapping("/{tripId}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID tripId, @RequestBody LinkRequestPayload payload) {
        LinkResponse linkResponse = this.linkService.registerLink(tripId, payload);
        return Optional.ofNullable(linkResponse)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Trip Id Not Found"));
    }

    @GetMapping("/{tripId}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID tripId) {
        List<LinkData> linkDataList = this.linkService.getAllLinksFromTrip(tripId);
        return ResponseEntity.ok(linkDataList);
    }
}
