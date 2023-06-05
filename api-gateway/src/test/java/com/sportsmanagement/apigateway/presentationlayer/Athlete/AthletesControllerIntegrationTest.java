package com.sportsmanagement.apigateway.presentationlayer.Athlete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AthletesControllerIntegrationTest {
//    private final String BASE_URI_ATHLETES = "/api/v1/athletes";
//    private String ATHLETE_SERVICE_BASE_URL =
//            "http://" + "localhost" + ":" + "8080" + "/api/v1/athletes";
//    private final String VALID_ATHLETE_ID = "0f5864b4-203b-4932-9e28-65df721b0793";
//    private String INVALID_ATHLETE_ID = "0f5864b4-203b-4932-9e28-65df721b0791";
//
//
//    @Autowired
//    WebTestClient webTestClient;
//
//
//    //post athlete
//
//    @Test
//    public void whenCreateAthleteWithValidValues_thenReturnNewAthlete() {
//        //arrange
//        String expectedTeamId = "123sport";
//        String expectedFirstName = "Stanwood";
//        String expectedLastName = "Mathet";
//        String expectedEmail = "smathet0@marketwatch.com";
//        String expectedSportName = "basketball";
//        Integer expectedPointsWon = 24;
//        Integer expectedGamesWon = 26;
//        Integer expectedGamesLost = 29;
//        Double expectedHeight = 70.3;
//        Double expectedWeight = 164.2;
//        Integer expectedAge = 20;
//        String expectedGender = "Male";
//        Status expectedStatus = Status.AVAILABLE;
//
//
//        AthleteRequestModel athleteRequestModel = new AthleteRequestModel(expectedTeamId, expectedFirstName, expectedLastName, expectedEmail, expectedSportName, expectedPointsWon, expectedGamesWon, expectedGamesLost, expectedHeight, expectedWeight, expectedAge, expectedGender, expectedStatus);
//        //act and assert
//        webTestClient.post()
//                .uri(ATHLETE_SERVICE_BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(athleteRequestModel)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(AthleteResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertNotNull(dto.getAthleteId());
//                    assertEquals(dto.getTeamId(), expectedTeamId);
//                    assertEquals(dto.getFirstName(), expectedFirstName);
//                    assertEquals(dto.getLastName(), expectedLastName);
//                    assertEquals(dto.getEmailAddress(), expectedEmail);
//                    assertEquals(dto.getSportName(), expectedSportName);
//                    assertEquals(dto.getPointsWon(), expectedPointsWon);
//                    assertEquals(dto.getGamesWon(), expectedGamesWon);
//                    assertEquals(dto.getGamesLost(), expectedGamesLost);
//                    assertEquals(dto.getHeight(), expectedHeight);
//                    assertEquals(dto.getWeight(), expectedWeight);
//                    assertEquals(dto.getAge(), expectedAge);
//                    assertEquals(dto.getGender(), expectedGender);
//                    assertEquals(dto.getStatus(), expectedStatus);
//                });
//    }
//
//    @Test
//    public void whenDeleteAthleteWithInvalidId_thenReturnNotFound() {
//        //arrange
//        String invalidAthleteId = INVALID_ATHLETE_ID;
//
//        //act and assert
//        webTestClient.delete()
//                .uri(ATHLETE_SERVICE_BASE_URL + "/" + invalidAthleteId)
//                .exchange()
//                .expectStatus().isNotFound();
//    }
//}