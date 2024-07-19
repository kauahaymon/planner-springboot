package com.rocketseat.planner.trip;

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

    @PostMapping
    public ResponseEntity<TripResponse> createNewTrip(@RequestBody TripRequestPayload payload) {
        TripResponse tripResponse = this.tripService.createNewTrip(payload);
        return ResponseEntity.ok(tripResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripDetails(@PathVariable UUID id) {
        Optional<Trip> optionalTrip = this.tripService.getTripDetails(id);
        return optionalTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        Optional<Trip> updatedTrip = this.tripService.updateTrip(id, payload);
        return updatedTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/confirm/{id}")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        Optional<Trip> confirmTrip = this.tripService.confirmTrip(id);
        return confirmTrip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
