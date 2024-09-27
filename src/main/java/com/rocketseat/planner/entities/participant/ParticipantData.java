package com.rocketseat.planner.entities.participant;

import java.util.UUID;

public record ParticipantData(UUID id, String name, String email, Boolean is_confirmed) {
}
