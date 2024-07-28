package com.rocketseat.planner.exception;

import java.time.Instant;

public record StandardErrorResponse(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path) {
}
