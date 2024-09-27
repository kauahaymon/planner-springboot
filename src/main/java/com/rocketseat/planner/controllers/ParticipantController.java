package com.rocketseat.planner.controllers;

import com.rocketseat.planner.entities.participant.Participant;
import com.rocketseat.planner.entities.participant.ParticipantRequestPayload;
import com.rocketseat.planner.services.ParticipantService;
import com.rocketseat.planner.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private ParticipantService participantService;

    @PostMapping("/{participantId}/confirm")
    public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID participantId, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.participantService.confirmParticipant(participantId, payload);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
