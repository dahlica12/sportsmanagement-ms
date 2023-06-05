package com.sportsmanagement.eventservice.domainclientlayer;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class TeamResponseModel extends RepresentationModel<TeamResponseModel>{

    String teamIdentifier;
    String sportId;
    String teamName;
    String sportType;
    String level;

}
