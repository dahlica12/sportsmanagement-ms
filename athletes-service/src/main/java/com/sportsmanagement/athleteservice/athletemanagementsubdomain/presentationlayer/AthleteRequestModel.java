package com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer;

import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class AthleteRequestModel  {


    private String teamId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String sportName;
    private Integer pointsWon;
    private Integer gamesWon;
    private Integer gamesLost;
    private Double height;
    private Double weight;
    private Integer age;
    private String gender;
    private Status status;


}
