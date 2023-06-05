package com.sportsmanagement.eventservice.datalayer;



import java.util.UUID;


public class SportIdentifier {

    private String sportId;

    public SportIdentifier(String sportId) {
        this.sportId = sportId;
    }

    public String getSportId() {
        return sportId;
    }
}


