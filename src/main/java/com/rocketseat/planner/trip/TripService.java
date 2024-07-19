package com.rocketseat.planner.trip;

import com.rocketseat.planner.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
