package com.sportsmanagement.eventservice.domainclientlayer;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsTeamResponseModel{

    String sportId;
    String name;
    //private List<TeamResponseModel> competitiveTeams;
}
