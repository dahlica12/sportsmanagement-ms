package com.sportsmanagement.apigateway.presentationlayer.Event;

import com.sportsmanagement.apigateway.businesslayer.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/sports/{sportId}/events") //need sport id to do a post
@RequiredArgsConstructor
public class SportEventController {

    private final EventService eventService;

    @PostMapping(
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<EventResponseModel> processSportEvent(
            @RequestBody EventRequestModel eventRequestModel, @PathVariable String sportId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventService.processSportEvent(eventRequestModel, sportId));
    }

}
