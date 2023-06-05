package com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team;

import jakarta.persistence.Embeddable;

@Embeddable
public class TeamIdentifier {

    private String teamId;

    public TeamIdentifier(){

    }
    public TeamIdentifier(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }
}
