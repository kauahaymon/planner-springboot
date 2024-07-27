package com.rocketseat.planner.trip;

import com.rocketseat.planner.participant.ParticipantRequestPayload;
import com.rocketseat.planner.participant.ParticipantResponse;
import com.rocketseat.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ParticipantService participantService;

    // TRIP
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

    // PARTICIPANT
    @PostMapping("/invite/{tripId}")
    public ResponseEntity<ParticipantResponse> inviteParticipant(@PathVariable UUID tripId, @RequestBody ParticipantRequestPayload payload) {
        ParticipantResponse participantResponse = this.participantService.inviteParticipant(tripId, payload);
        return Optional.ofNullable(participantResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
