package com.sportsmanagement.apigateway.presentationlayer.Event;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EventResponseModel {

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("sportId")
    private String sportId;

    @JsonProperty("teamId")
    private String teamId;

    @JsonProperty("athleteId")
    private String athleteId;

    @JsonProperty("coachId")
    private String coachId;

    @JsonProperty("team1Name")
    private String team1Name;

    @JsonProperty("firstName")
    private String team2Name;

    @JsonProperty("coach1LastName")
    private String coach1LastName;

    @JsonProperty("coach2LastName")
    private String coach2LastName;

    @JsonProperty("eventType")
    private EventType eventType;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("eventDate")
    private LocalDate eventDate;

    @JsonProperty("eventAddress")
    private Address eventAddress;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("score")
    private String score;


    public EventResponseModel() {
        this.eventId = "";
        this.sportId = "";
        this.teamId = "";
        this.athleteId = "";
        this.coachId = "";
        this.team1Name = "";
        this.team2Name = "";
        this.coach1LastName = "";
        this.coach2LastName = "";
        this.eventType = EventType.GAME;
        this.status = Status.COMING_UP;
        this.eventDate = LocalDate.now();
        this.eventAddress = new Address();
        this.duration = "";
        this.score = "";
    }


}
