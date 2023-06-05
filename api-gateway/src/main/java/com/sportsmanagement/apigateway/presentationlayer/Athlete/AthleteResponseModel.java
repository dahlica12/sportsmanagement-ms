package com.sportsmanagement.apigateway.presentationlayer.Athlete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class AthleteResponseModel extends RepresentationModel<AthleteResponseModel> {
    @JsonProperty("athleteId")
    private final String athleteId;

    @JsonProperty("teamId")
    private final String teamId;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("lastName")
    private final String lastName;

    @JsonProperty("emailAddress")
    private final String emailAddress;

    @JsonProperty("sportName")
    private final String sportName;

//    @JsonProperty("stats")
//    private final Stats stats;

    @JsonProperty("height")
    private final Double height;

    @JsonProperty("weight")
    private final Double weight;

    @JsonProperty("age")
    private final Integer age;

    @JsonProperty("gender")
    private final String gender;

    @JsonProperty("status")
    private final Status status;

    @JsonProperty("pointsWon")
    private Integer pointsWon;

    @JsonProperty("gamesWon")
    private Integer gamesWon;

    @JsonProperty("gamesLost")
    private Integer gamesLost;




    public AthleteResponseModel(){
        this.athleteId = "";
        this.teamId = "";
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.sportName = "";
        this.height = 0.0;
        this.weight = 0.0;
        this.age = 0;
        this.gender = "";
        this.status = Status.UNAVAILABLE;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.pointsWon = 0;
    }


}
