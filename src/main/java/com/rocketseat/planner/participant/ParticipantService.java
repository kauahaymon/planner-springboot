package com.rocketseat.planner.participant;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {
    public void registerParticipantsToEvent(List<String> participantsToInvite, UUID tripId) {}

    public void triggerConfirmationToParticipants(UUID tripId) {}
}