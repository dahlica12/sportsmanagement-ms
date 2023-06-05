package com.sportsmanagement.eventservice.domainclientlayer;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AthleteResponseModel {

    String athleteId;
    String teamId;
    String firstName;
    String lastName;
    String emailAddress;
    String sportName;
    Integer pointsWon;
    Integer gamesWon;
    Integer gamesLost;
    Float height;
    Float weight;
    Integer age;
    String gender;



}
