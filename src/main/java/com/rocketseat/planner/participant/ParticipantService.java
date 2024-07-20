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

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.participantRepository.saveAll(participants);

        System.out.println(participants.get(0).getId());
    }

    public void triggerConfirmationToParticipants(UUID tripId) {}
}
