package com.sportsmanagement.apigateway.presentationlayer.Event;

import com.sportsmanagement.apigateway.businesslayer.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor //does injection for us
public class EventController {

   private final EventService eventService;


    @GetMapping
    ResponseEntity<List<EventResponseModel>> getAllEvents() {
       return ResponseEntity.ok().body(eventService.getAllEvents());
    }

}


