
package com.sportsmanagement.eventservice.utils;

import com.sportsmanagement.eventservice.datalayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class DatabaseLoaderService implements CommandLineRunner {

    @Autowired
    EventRepository eventRepository;



    @Override
    public void run(String... args) throws Exception {

        var eventIdentifier1 = new EventIdentifier();
        var teamIdentifier1 = new TeamIdentifier("123sport");
        var athleteIdentifier1 = new AthleteIdentifier("693db96d-ac78-468a-87ba-60f00191f358");
        var coachIdentifier1 = new CoachIdentifier("b15c35ae-c3c1-4c2c-ac5d-0a63277e9389");
        var sportIdentifier1 = new SportIdentifier("514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3");
        var address1 = Address.builder()
                .streetAddress("5467 rue Jesein")
                .city("Lakeland")
                .province("Quebec")
                .country("Canada")
                .postalCode("T13 H74")
                .build();

        var eventIdentifier2 = new EventIdentifier();
        var teamIdentifier2 = new TeamIdentifier("321sport");
        var athleteIdentifier2 = new AthleteIdentifier("121e09e8-cfae-4f18-a52d-e88fc8daed0f");
        var coachIdentifier2 = new CoachIdentifier("70736e5a-abb1-4cac-b546-f9c2b7669d51");
        var sportIdentifier2 = new SportIdentifier("0e68ddb1-aa16-41bc-8aad-c9e8c80f3efd");
        var address2 = Address.builder()
                .streetAddress("9124 rue Fishy")
                .city("Bryyard")
                .province("Quebec")
                .country("Canada")
                .postalCode("J7R C2B")
                .build();

        //first event
        var event1 = Event.builder()
                .eventIdentifier(eventIdentifier1)
                .teamIdentifier(teamIdentifier1)
                .sportIdentifier(sportIdentifier1)
                .athleteIdentifier(athleteIdentifier1)
                .coachIdentifier(coachIdentifier1)
                .team1Name("Jersey Tigers")
                .team2Name("Ottawa Otters")
                .coach1LastName("Mathet")
                .coach2LastName("Briana")
                .eventType(EventType.GAME)
                .status(Status.COMING_UP)
                .eventDate(LocalDate.of(2023, 06, 8))
                .eventAddress(address2)
                .duration("90 minutes")
                .score("TBA")
                .build();

        var event2 = Event.builder()
                .eventIdentifier(eventIdentifier2)
                .teamIdentifier(teamIdentifier2)
                .sportIdentifier(sportIdentifier2)
                .athleteIdentifier(athleteIdentifier2)
                .coachIdentifier(coachIdentifier2)
                .team1Name("Montreal Minotaurs")
                .team2Name("Toronto Elephants")
                .coach1LastName("Malloch")
                .coach2LastName("Mitchall")
                .eventType(EventType.GAME)
                .status(Status.IN_PROGRESS)
                .eventDate(LocalDate.of(2023, 05, 14))
                .eventAddress(address1)
                .duration("90 minutes")
                .score("TBA")
                .build();

        eventRepository.insert(event1);
        eventRepository.insert(event2);
    }
}


