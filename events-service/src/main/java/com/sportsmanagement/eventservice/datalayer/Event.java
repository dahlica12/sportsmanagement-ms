package com.sportsmanagement.eventservice.datalayer;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document(collection = "events")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    private String id;
    private EventIdentifier eventIdentifier;
    private SportIdentifier sportIdentifier;
    private TeamIdentifier teamIdentifier;
    private AthleteIdentifier athleteIdentifier;
    private CoachIdentifier coachIdentifier;
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


