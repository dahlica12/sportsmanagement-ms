package com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamRequestModel {

    private String teamId;
    private String sportId;
    private String teamName;
    private String sportType;
    private String level;

}
