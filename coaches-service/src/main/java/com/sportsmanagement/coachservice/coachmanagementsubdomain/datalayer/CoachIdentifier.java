package com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class CoachIdentifier {

    private String coachId;

    public CoachIdentifier() {
        this.coachId = UUID.randomUUID().toString();
    }

    public String getCoachId() {
        return coachId;
    }
}
