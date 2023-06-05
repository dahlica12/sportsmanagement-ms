package com.sportsmanagement.eventservice.datalayer;



import java.util.UUID;


public class CoachIdentifier {

    private String coachId;

    public CoachIdentifier(String coachId) {
        this.coachId = coachId;
    }

    public String getCoachId() {
        return coachId;
    }
}
