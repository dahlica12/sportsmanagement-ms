package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@Value
@AllArgsConstructor
public class TeamResponseModel extends RepresentationModel<TeamResponseModel> {
    @JsonProperty("teamIdentifier")
    String teamIdentifier;

    @JsonProperty("sportId")
    String sportId;
    @JsonProperty("teamName")
    String teamName;
    @JsonProperty("sportType")
    String sportType;
    @JsonProperty("level")
    Level level;

    public TeamResponseModel(){
        this.level = Level.RECREATIONAL;
        this.teamIdentifier = "";
        this.sportType = "";
        this.teamName = "";
        this.sportId = "";
    }

}
