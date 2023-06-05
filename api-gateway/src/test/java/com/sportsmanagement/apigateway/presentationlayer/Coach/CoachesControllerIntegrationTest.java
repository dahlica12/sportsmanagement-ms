package com.sportsmanagement.apigateway.presentationlayer.Coach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//class CoachesControllerIntegrationTest {
//
//    private final String BASE_URI_COACHES = "http://" + "localhost" + ":" + "8080" +"/api/v1/coaches";
//    private final String VALID_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389";
//    private final String VALID_COACH_TITLE = "Surveyor";
//
//    private String INVALID_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389nog";
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    @Test
//    public void whenCoachesExist_thenReturnAllCoaches() {
//        Integer expectedNumCoaches = 6;
//
//        webTestClient.get()
//                .uri(BASE_URI_COACHES)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody()
//                .jsonPath("$.length()").isEqualTo(expectedNumCoaches);
//    }
//
//    //post coach
//
//    @Test
//    public void whenCreateCoachWithValidValues_thenReturnNewCoach() {
//        //arrange
//        String expectedTeamId = "123sport";
//        String expectedFirstName = "John";
//        String expectedLastName = "Doe";
//        String expectedEmail = "JohnDoe@gmail.com";
//        String expectedPhoneNumber = "514-999-9999";
//        Double expectedSalary = 12000.00;
//        String expectedTitle = "manager";
//
//        CoachRequestModel coachRequestModel = new CoachRequestModel(expectedTeamId, expectedFirstName, expectedLastName, expectedEmail, expectedPhoneNumber, expectedSalary, expectedTitle);
//
//        //act and assert
//        webTestClient.post()
//                .uri(BASE_URI_COACHES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(coachRequestModel)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(CoachResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertNotNull(dto.getCoachId());
//                    assertEquals(dto.getTeamId(), expectedTeamId);
//                    assertEquals(dto.getFirstName(), expectedFirstName);
//                    assertEquals(dto.getLastName(), expectedLastName);
//                    assertEquals(dto.getEmailAddress(), expectedEmail);
//                    assertEquals(dto.getPhoneNumber(), expectedPhoneNumber);
//                    assertEquals(dto.getSalary(), expectedSalary);
//                    assertEquals(dto.getTitle(), expectedTitle);
//                });
//    }
//}