package com.rocketseat.planner.trip;

import com.rocketseat.planner.activity.ActivityData;
import com.rocketseat.planner.activity.ActivityRequestPayload;
import com.rocketseat.planner.activity.ActivityResponse;
import com.rocketseat.planner.activity.ActivityService;
import com.rocketseat.planner.link.LinkRequestPayload;
import com.rocketseat.planner.link.LinkResponse;
import com.rocketseat.planner.link.LinkService;
import com.rocketseat.planner.participant.ParticipantData;
import com.rocketseat.planner.participant.ParticipantRequestPayload;
import com.rocketseat.planner.participant.ParticipantResponse;
import com.rocketseat.planner.participant.ParticipantService;
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
        return optionalTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID tripId, @RequestBody TripRequestPayload payload) {
        Optional<Trip> updatedTrip = this.tripService.updateTrip(tripId, payload);
        return updatedTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/confirm/{tripId}")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID tripId) {
        Optional<Trip> confirmTrip = this.tripService.confirmTrip(tripId);
        return confirmTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PARTICIPANTS
    @PostMapping("/invite/{tripId}")
    public ResponseEntity<ParticipantResponse> inviteParticipant(@PathVariable UUID tripId, @RequestBody ParticipantRequestPayload payload) {
        ParticipantResponse participantResponse = this.participantService.inviteParticipant(tripId, payload);
        return Optional.ofNullable(participantResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/participants/{tripId}")
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
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID id) {
        List<ActivityData> activityDataList = this.activityService.getAllActivitiesFromTrip(id);
        return ResponseEntity.ok(activityDataList);
    }

    // LINKS
    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload) {
        LinkResponse linkResponse = this.linkService.registerLink(id, payload);
        return Optional.ofNullable(linkResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
