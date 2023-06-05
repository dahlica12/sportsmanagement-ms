package com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public class AthleteIdentifier {

    private String athleteId;


    public AthleteIdentifier()  {
        this.athleteId = UUID.randomUUID().toString();
    }



    public String getAthleteId() {
        return athleteId;
    }


}
