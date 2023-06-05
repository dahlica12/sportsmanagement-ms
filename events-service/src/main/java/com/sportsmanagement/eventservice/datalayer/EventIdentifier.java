package com.sportsmanagement.eventservice.datalayer;


import org.springframework.data.mongodb.core.index.Indexed;

import java.util.UUID;


public class EventIdentifier {

    @Indexed(unique = true)
    private String eventId;


    public EventIdentifier(){
        this.eventId = UUID.randomUUID().toString();
    }

    public String getEventId(){
        return eventId;

    }
}


