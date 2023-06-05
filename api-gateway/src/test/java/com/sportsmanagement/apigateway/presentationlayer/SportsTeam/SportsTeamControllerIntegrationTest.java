package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;

import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
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
//class SportsTeamControllerIntegrationTest {
//
//    private final String SPORT_SERVICE_BASE_URL = "http://" + "localhost" + ":" + "8080" +"/api/v1/sports";
//    private final String VALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3";
//    private final String INVALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3tv7lwrong";
//
//    @Autowired
//    WebTestClient webTestClient;
//
//
//    @Test
//    public void whenCreateSportWithValidValues_thenReturnNewSport() {
//        //arrange
//        String expectedName = "golf";
//
//
//        SportRequestModel sportRequestModel = new SportRequestModel(expectedName);
//        //act and assert
//        webTestClient.post()
//                .uri(SPORT_SERVICE_BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(sportRequestModel)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(SportsTeamResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertNotNull(dto.getSportId());
//                    assertEquals(dto.getName(), expectedName);
//
//                });
//    }
//}