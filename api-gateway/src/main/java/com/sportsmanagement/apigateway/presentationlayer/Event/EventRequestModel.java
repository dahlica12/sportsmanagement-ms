package com.sportsmanagement.apigateway.presentationlayer.Event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
public class EventRequestModel {



    //private String eventId;
    //private String sportId;
    private String teamId;
    private String athleteId;
    private String coachId;
    @JsonProperty("eventType")
    private EventType eventType;
    @JsonProperty("status")
    private Status status;
    private LocalDate eventDate;
    private String streetAddress;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String duration;
    private String score;


}
