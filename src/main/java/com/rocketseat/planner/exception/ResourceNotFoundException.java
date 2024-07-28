package com.rocketseat.planner.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
