package com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SportsLeaguePersistenceTest {

    private SportsLeague preSavedSport;
    private final String VALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3";
    private final String INVALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3tv7lwrong";


    @Autowired
    SportRepository sportRepository;

    @BeforeEach
    public void setup() {
        sportRepository.deleteAll();
        preSavedSport = sportRepository.save(new SportsLeague("field hockey"));
    }

    @Test
    public void saveNewSport_ShouldSucceed() {
        //arrange
        String expectedName = "golf";
        SportsLeague sportsLeague = new SportsLeague(expectedName);



    }
}