package com.sportsmanagement.eventservice.datalayer;



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
