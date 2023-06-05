package com.sportsmanagement.eventservice.datalayer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface EventRepository extends MongoRepository<Event, String> {

    //Event findSportEventBy_SportId(String sportId);
    // findAthleteEventBy_AthleteId(String athleteId);

}
