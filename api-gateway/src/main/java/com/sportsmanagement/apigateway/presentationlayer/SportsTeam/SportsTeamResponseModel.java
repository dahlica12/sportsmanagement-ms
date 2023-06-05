package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@Value
@AllArgsConstructor
public class SportsTeamResponseModel extends RepresentationModel<SportsTeamResponseModel> {

    @JsonProperty("sportId")
    String sportId;
    @JsonProperty("name")
    String name;

    public SportsTeamResponseModel(){
        this.sportId = "";
        this.name = "";
    }
}
