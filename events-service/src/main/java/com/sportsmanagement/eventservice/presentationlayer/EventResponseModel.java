package com.sportsmanagement.eventservice.presentationlayer;

import com.sportsmanagement.eventservice.datalayer.Address;
import com.sportsmanagement.eventservice.datalayer.EventType;
import com.sportsmanagement.eventservice.datalayer.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventResponseModel {

    private String eventId;
    private String sportId;
    private String teamId;
    private String athleteId;
    private String coachId;
    private String team1Name;
    private String team2Name;
    private String coach1LastName;
    private String coach2LastName;
    private EventType eventType;
    private Status status;
    private LocalDate eventDate;
    private Address eventAddress;
    private String duration;
    private String score;

}
