package com.rocketseat.planner.trip;

import com.rocketseat.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ParticipantService participantService;

    public TripResponse createNewTrip(TripRequestPayload payload) {
        Trip newTrip = new Trip(payload);
        this.tripRepository.save(newTrip);
        this.participantService.registerParticipantsToEvent(payload.emails_to_invite(), newTrip.getId());
        return new TripResponse(newTrip.getId());
    }

    public Optional<Trip> getTripDetails(UUID id) {
        return this.tripRepository.findById(id);
    }

    public Optional<Trip> updateTrip(UUID id, TripRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip updatedTrip = trip.get();
            updatedTrip.setDestination(payload.destination());
            updatedTrip.setStartsAt(payload.starts_at());
            updatedTrip.setEndsAt(payload.ends_at());
            this.tripRepository.save(updatedTrip);
            return Optional.of(updatedTrip);
        }
        return Optional.empty();
    }

    public Optional<Trip> confirmTrip(UUID id) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if (trip.isPresent()) {
            Trip confirmedTrip = trip.get();
            confirmedTrip.setIsConfirmed(true);
            this.tripRepository.save(confirmedTrip);
            this.participantService.triggerConfirmationToParticipants(id);
            return Optional.of(confirmedTrip);
        }
        return Optional.empty();
    }
}
