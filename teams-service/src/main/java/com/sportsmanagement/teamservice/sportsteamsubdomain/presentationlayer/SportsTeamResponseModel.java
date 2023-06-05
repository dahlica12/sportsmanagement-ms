package com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SportsTeamResponseModel {

    private String sportId;
    private String name;
    //private List<TeamResponseModel> competitiveTeams;
}
