package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.hateoas.RepresentationModel;

@Value
@AllArgsConstructor
public class SportResponseModel extends RepresentationModel<SportResponseModel> {

    String sportId;
    String name;
}
