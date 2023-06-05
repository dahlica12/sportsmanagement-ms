package com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CoachPersistenceTest {

    private Coach preSavedCoach;
    private Coach coach2;
    private Coach coach3;
    String FOUND_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389";
    String NOT_FOUND_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389nog";
    Integer expectedNumCoaches = 6;

    @Autowired
    CoachRepository coachRepository;

    @BeforeEach
    public void setup() {
        coachRepository.deleteAll();
        preSavedCoach = coachRepository.save(new Coach("lead-coach"));
    }

    @Test
    public void saveNewCoach_ShouldSucceed() {
        //arrange
        String expectedTitle = "Surveyor";
        Coach coach = new Coach(expectedTitle);


        //act
        Coach savedCoach= coachRepository.save(coach);

        //assert
        assertNotNull(savedCoach);
        assertNotNull(savedCoach.getCoachIdentifier().getCoachId());
        assertNotNull(savedCoach.getId());
        assertNotNull(expectedTitle, savedCoach.getTitle());
    }

    @Test
    public void updateCoach_ShouldSucceed() {
        //arrange
        String updatedTitle = "assistant-coach";
        preSavedCoach.setTitle(updatedTitle);

        //act
        Coach updatedCoach = coachRepository.save(preSavedCoach);

        //assert
        assertThat(updatedCoach, samePropertyValuesAs(preSavedCoach)); //hamcrest
        assertNotEquals("top-coach", updatedCoach.getTitle());
    }

    @Test
    public void deleteCoach_ShouldSucceed() {
        //act
        coachRepository.delete(preSavedCoach);

        Boolean found = coachRepository.existsByCoachIdentifier_CoachId
                (preSavedCoach.getCoachIdentifier().getCoachId());
        //assert
        assertFalse(found);
    }

    @Test
    public void findByCoachIdentifier_CoachId_ShouldSucceed() {
        //act
        Coach found = coachRepository.findByCoachIdentifier_CoachId
                (preSavedCoach.getCoachIdentifier().getCoachId());

        //assert
        assertNotNull(found);
        assertThat(preSavedCoach, samePropertyValuesAs(found));

    }

    @Test
    public void findByInvalidCoachIdentifier_CoachId_ShouldReturnNull() {
        Coach found = coachRepository.findByCoachIdentifier_CoachId
                (preSavedCoach.getCoachIdentifier().getCoachId() + 1);

        //assert
        assertNull(found);
    }

    @Test
    public void existsByCoachIdentifier_CoachId_ShouldReturnTrue() {
        //act
        Boolean found = coachRepository.existsByCoachIdentifier_CoachId
                (preSavedCoach.getCoachIdentifier().getCoachId());

        //assert
        assertTrue(found);
    }

    @Test
    public void existsByInvalidCoachIdentifier_CoachId_ShouldReturnFalse() {
        //act
        Boolean found = coachRepository.existsByCoachIdentifier_CoachId
                (preSavedCoach.getCoachIdentifier().getCoachId()+1);

        //assert
        assertFalse(found);
    }

//    @Test
//    public void findByFirstName_ShouldReturnCoachesWithMatchingFirstName() {
//        //arrange
//        String firstName = "Eugenio";
//        List<Coach> expectedCoaches = Arrays.asList(preSavedCoach);
//
//        //act
//        List<Coach> coachesByFirstName = coachRepository.findByFirstName(firstName);
//
//        //assert
//        assertEquals(expectedCoaches.size(), coachesByFirstName.size());
//        assertTrue(coachesByFirstName.containsAll(expectedCoaches));
//    }
//
//    @Test
//    public void findByLastName_ShouldReturnCoachesWithMatchingLastName() {
//        //arrange
//        String lastName = "Malloch";
//        List<Coach> expectedCoaches = Arrays.asList(coach2);
//
//        //act
//        List<Coach> coachesByLastName = coachRepository.findByLastName(lastName);
//
//        //assert
//        assertEquals(expectedCoaches.size(), coachesByLastName.size());
//        assertTrue(coachesByLastName.containsAll(expectedCoaches));
//    }

    @Test
    public void findByInvalidEmailAddress_ShouldReturnEmptyList() {
        //arrange
        String invalidEmailAddress = "Invalid";

        //act
        List<Coach> coachesByInvalidEmailAddress = coachRepository.findByEmailAddress(invalidEmailAddress);

        //assert
        assertTrue(coachesByInvalidEmailAddress.isEmpty());
    }

    @Test
    public void findByInvalidFirstName_ShouldReturnEmptyList() {
        //arrange
        String invalidFirstName = "Invalid";

        //act
        List<Coach> coachesByInvalidFirstName = coachRepository.findByFirstName(invalidFirstName);

        //assert
        assertTrue(coachesByInvalidFirstName.isEmpty());
    }

    @Test
    public void findByInvalidLastName_ShouldReturnEmptyList() {
        //arrange
        String invalidLastName = "Invalid";

        //act
        List<Coach> coachesByInvalidLastName = coachRepository.findByLastName(invalidLastName);

        //assert
        assertTrue(coachesByInvalidLastName.isEmpty());
    }



    @Test
    public void findByInvalidCoachId_ShouldReturnNull() {
        //arrange
        String invalidCoachId = "invalid-coach-id";

        //act
        Coach coachByInvalidCoachId = coachRepository.findByCoachIdentifier_CoachId(invalidCoachId);

        //assert
        assertNull(coachByInvalidCoachId);
    }

    @Test
    public void existsByInvalidCoachId_ShouldReturnFalse() {
        //arrange
        String invalidCoachId = "invalid-coach-id";

        //act
        boolean existsByInvalidCoachId = coachRepository.existsByCoachIdentifier_CoachId(invalidCoachId);

        //assert
        assertFalse(existsByInvalidCoachId);
    }






}