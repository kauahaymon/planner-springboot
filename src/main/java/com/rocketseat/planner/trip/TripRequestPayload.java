package com.rocketseat.planner.trip;

import java.time.LocalDateTime;
import java.util.List;

public record TripRequestPayload(
        String destination,
        LocalDateTime starts_at,
        LocalDateTime ends_at,
        List<String> emails_to_invite,
        Boolean is_confirmed,
        String owner_name,
        String owner_email) {
}
