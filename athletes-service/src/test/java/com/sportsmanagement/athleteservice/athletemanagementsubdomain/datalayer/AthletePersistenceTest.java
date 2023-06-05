package com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AthletePersistenceTest {

    private Athlete preSavedAthlete;
    String FOUND_ATHLETE_ID = "8986e2c8-0b3b-4fff-9b0b-30d7e5cc8982";
    String NOT_FOUND_ATHLETE_ID = "693db96d-ac78-468a-87ba-60f00191f358oof";

    @Autowired
    AthleteRepository athleteRepository;

    @BeforeEach
    public void setup() {
        athleteRepository.deleteAll();
        preSavedAthlete = athleteRepository.save(new Athlete("play-ball"));
    }

//    @Test
//    public void saveNewAthlete_ShouldSucceed() {
//        //arrange
//        String expectedSport = "Cricket";
////        String expectedTeamId = "123sport";
////        String expectedFirstName = "Stanwood";
////        String expectedLastName = "Mathet";
////        String expectedEmail = "smathet0@marketwatch.com";
////        String expectedSportName = "basketball";
////        String expectedPointsWon = "24";
////        String expectedGamesWon = "26";
////        String expectedGamesLost = "29";
////        String expectedHeight = "70.3";
////        String expectedWeight = "164.2";
////        String expectedAge = "20";
////        String expectedGender = "Male";
////        String expectedStatus = "AVAILABLE";
//
////        Athlete teamid = new Athlete(expectedTeamId);
////        Athlete firstname = new Athlete(expectedFirstName);
////        Athlete lastname = new Athlete(expectedLastName);
////        Athlete email = new Athlete(expectedEmail);
////        Athlete  sportname = new Athlete(expectedSportName);
////        Athlete  pointswon = new Athlete(expectedPointsWon);
////        Athlete  gameswon = new Athlete(expectedGamesWon);
////        Athlete  gameslost = new Athlete(expectedGamesLost);
////        Athlete  height = new Athlete(expectedHeight);
////        Athlete  weight = new Athlete(expectedWeight);
////        Athlete  age = new Athlete(expectedAge);
////        Athlete  gender = new Athlete(expectedGender);
////        Athlete  status = new Athlete(expectedStatus);
//
//
//        Athlete athlete = new Athlete(expectedSport);
//
//
//        //act
//        Athlete savedAthlete = athleteRepository.save(athlete);
//
//        //assert
//        assertNotNull(savedAthlete);
//        assertNotNull(savedAthlete.getAthleteIdentifier().getAthleteId());
//        assertNotNull(savedAthlete.getId());
//        assertNotNull(expectedSport, savedAthlete.getSportName());
//    }

    @Test
    public void updateAthlete_ShouldSucceed() {
        //
        String updatedSport = "Tennis";
        preSavedAthlete.setSportName(updatedSport);

        Athlete updatedAthlete = athleteRepository.save(preSavedAthlete);

        assertThat(updatedAthlete, samePropertyValuesAs(preSavedAthlete));
        assertNotEquals("quidditch", updatedAthlete.getSportName());
    }

    @Test void deleteAthlete_ShouldSucceed() {
        //act
        athleteRepository.delete(preSavedAthlete);

        Boolean found = athleteRepository.existsByAthleteIdentifier_AthleteId
                (FOUND_ATHLETE_ID);

        //assert
        assertFalse(found);
    }

//    @Test
//    public void findByAthleteIdentifier_AthleteId_ShouldSucceed() {
//        //act
//        Athlete found = athleteRepository.findByAthleteIdentifier_AthleteId
//                (preSavedAthlete.getAthleteIdentifier().getAthleteId());
//
//        //assert
//        assertNotNull(found);
//        assertThat(FOUND_ATHLETE_ID, samePropertyValuesAs(found));
//    }


    @Test
    public void findByInvalidAthleteIdentifier_AthleteId_ShouldReturnNull() {
        Athlete found = athleteRepository.findByAthleteIdentifier_AthleteId
                (NOT_FOUND_ATHLETE_ID);
        assertNull(found);
    }

//    @Test
//    public void existsByAthleteIdentifier_AthleteId_ShouldReturnTrue() {
//        Boolean found = athleteRepository.existsByAthleteIdentifier_AthleteId
//                (preSavedAthlete.getAthleteIdentifier().getAthleteId());
//
//        assertTrue(found);
//    }
//
//    @Test
//    public void existsByAthleteIdentifier_AthleteId_ShouldReturnFalse() {
//        Boolean found = athleteRepository.existsByAthleteIdentifier_AthleteId(preSavedAthlete.getAthleteIdentifier().getAthleteId()+1);
////                athleteRepository.existsByAthleteIdentifier_AthleteId
////                (preSavedAthlete.getAthleteIdentifier().getAthleteId());
//
//        assertFalse(found);
//    }



    @Test
    public void existsByInvalidAthleteIdentifier_AthleteId_ShouldReturnFalse() {
        //arrange
        String invalidAthleteId = "invalid-athlete-id";

        //act
        boolean exists = athleteRepository.existsByAthleteIdentifier_AthleteId(invalidAthleteId);

        //assert
        assertFalse(exists);
    }

    @Test
    public void findByInvalidEmailAddress_ShouldReturnEmptyList() {
        //arrange
        String invalidEmailAddress = "Invalid";

        //act
        List<Athlete> athletesByInvalidEmailAddress = athleteRepository.findByEmailAddress(invalidEmailAddress);

        //assert
        assertTrue(athletesByInvalidEmailAddress.isEmpty());
    }

    @Test
    public void findByInvalidFirstName_ShouldReturnEmptyList() {
        //arrange
        String invalidFirstName = "Invalid";

        //act
        List<Athlete> athletesByInvalidFirstName = athleteRepository.findByFirstName(invalidFirstName);

        //assert
        assertTrue(athletesByInvalidFirstName.isEmpty());
    }

    @Test
    public void findByInvalidLastName_ShouldReturnEmptyList() {
        //arrange
        String invalidLastName = "Invalid";

        //act
        List<Athlete> athletesByInvalidLastName = athleteRepository.findByLastName(invalidLastName);

        //assert
        assertTrue(athletesByInvalidLastName.isEmpty());
    }



    @Test
    public void findByInvalidAthleteId_ShouldReturnNull() {


        //act
        Athlete athleteByInvalidAthleteId = athleteRepository.findByAthleteIdentifier_AthleteId(NOT_FOUND_ATHLETE_ID);

        //assert
        assertNull(athleteByInvalidAthleteId);
    }

    @Test
    public void existsByInvalidAthleteId_ShouldReturnFalse() {


        //act
        boolean existsByInvalidAthleteId = athleteRepository.existsByAthleteIdentifier_AthleteId(NOT_FOUND_ATHLETE_ID);

        //assert
        assertFalse(existsByInvalidAthleteId);
    }

}