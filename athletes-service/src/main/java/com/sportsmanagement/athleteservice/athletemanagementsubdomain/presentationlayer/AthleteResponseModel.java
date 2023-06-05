package com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer;

import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Stats;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AthleteResponseModel {

    private final String athleteId;
    private final String teamId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String sportName;
    //private final Stats stats;
    private final Integer pointsWon;
    private final Integer gamesWon;
    private final Integer gamesLost;
    private final Double height;
    private final Double weight;
    private final Integer age;
    private final String gender;
    private final Status status;



}
