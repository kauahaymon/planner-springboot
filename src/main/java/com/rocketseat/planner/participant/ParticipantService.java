package com.rocketseat.planner.participant;

import com.rocketseat.planner.trip.Trip;
import com.rocketseat.planner.trip.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private TripRepository tripRepository;


    public Optional<Participant> confirmParticipant(UUID participantId, ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.participantRepository.findById(participantId);
        if (participant.isPresent()) {
            Participant confirmedParticipant = participant.get();
            confirmedParticipant.setIsConfirmed(true);
            confirmedParticipant.setName(payload.name());

            this.participantRepository.save(confirmedParticipant);
            return Optional.of(confirmedParticipant);
        }
        return Optional.empty();
    }

    // Register a participant to event
    public ParticipantResponse registerParticipantToEvent(String email, Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        this.participantRepository.save(newParticipant);
        return new ParticipantResponse(newParticipant.getId());
    }

    // Invite New Participant
    public ParticipantResponse inviteParticipant(UUID id, ParticipantRequestPayload payload) {
        Optional<Trip> trip = this.tripRepository.findById(id);
        if(trip.isPresent()) {
            Trip rawTrip = trip.get();
            ParticipantResponse participantResponse = registerParticipantToEvent(payload.email(), rawTrip);

            if (rawTrip.getIsConfirmed()) triggerEmailConfirmationToParticipant(payload.email());

            return participantResponse;
        }
        return null;
    }

    // Retrieve Participants
    public List<ParticipantData> getAllParticipantsFromTrip(UUID id) {
        return this.participantRepository
                .findByTripId(id)
                .stream()
                .map(
                        participant -> new ParticipantData(
                        participant.getId(),
                        participant.getName(),
                        participant.getEmail(),
                        participant.getIsConfirmed())
                )
                .toList();
    }

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.participantRepository.saveAll(participants);

        System.out.println(participants.get(0).getId());
    }

    public void triggerConfirmationToParticipants(UUID tripId) {}
    public void triggerEmailConfirmationToParticipant(String email) {};
}
