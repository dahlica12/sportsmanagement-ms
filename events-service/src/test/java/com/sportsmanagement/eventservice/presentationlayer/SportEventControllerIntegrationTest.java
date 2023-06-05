package com.sportsmanagement.eventservice.presentationlayer;

import com.sportsmanagement.eventservice.datalayer.EventType;
import com.sportsmanagement.eventservice.datalayer.Status;
import com.sportsmanagement.eventservice.domainclientlayer.SportRequestModel;
import com.sportsmanagement.eventservice.domainclientlayer.SportsTeamResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//class SportEventControllerIntegrationTest {
//
//    private final String EVENT_SERVICE_BASE_URL = "http://" + "localhost" + ":" + "8095" + "/api/v1/";
//    private final String VALID_EVENT_ID = "cbd3444f-0e02-465c-8e62-10a44a0afd88";
//    private final String VALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3";
//    @Autowired
//    WebTestClient webTestClient;
//
//
//    //post coach
//
//    @Test
//    public void whenCreateSportEventWithValidValues_thenReturnNewSportEvent() {
//        //arrange
//        String expectedTeamId = "123sport";
//        String expectedAthleteId = "693db96d-ac78-468a-87ba-60f00191f358";
//        String expectedCoachId = "a67589e1-7a67-4508-9b2c-e1e7621493bb";
//        EventType expectedEventType = EventType.GAME;
//        Status expectedStatus = Status.COMING_UP;
//        LocalDate expectedEventDate= LocalDate.parse("2023-05-26");
//        String expectedStreetAddress = "1122 park avenue";
//        String expectedCity = "Delta";
//        String expectedProvince = "Quebec";
//        String expectedCountry = "Canada";
//        String expectedPostalCode = "J2R 4DE";
//        String expectedDuration = "90 minutes";
//        String expectedScore = "TBA";
//
//        EventRequestModel eventRequestModel = new EventRequestModel(expectedTeamId, expectedAthleteId, expectedCoachId, expectedEventType, expectedStatus, expectedEventDate,
//                expectedStreetAddress, expectedCity, expectedProvince, expectedCountry, expectedPostalCode, expectedDuration, expectedScore);
//
//        //act and assert
//        webTestClient.post()
//                .uri(EVENT_SERVICE_BASE_URL + "sports/" + VALID_SPORT_ID +"/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(eventRequestModel)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(EventResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertNotNull(dto.getCoachId());
//                    assertEquals(dto.getTeamId(), expectedTeamId);
//                    assertEquals(dto.getAthleteId(), expectedAthleteId);
//                    assertEquals(dto.getCoachId(), expectedCoachId);
//                    assertEquals(dto.getEventType(), expectedEventType);
//                    assertEquals(dto.getStatus(), expectedStatus);
//                    assertEquals(dto.getEventDate(), expectedEventDate);
//                    assertEquals(dto.getEventAddress().getStreetAddress(), expectedStreetAddress);
//                    assertEquals(dto.getEventAddress().getCity(), expectedCity);
//                    assertEquals(dto.getEventAddress().getProvince(), expectedProvince);
//                    assertEquals(dto.getEventAddress().getCountry(), expectedCountry);
//                    assertEquals(dto.getEventAddress().getPostalCode(), expectedPostalCode);
//                    assertEquals(dto.getDuration(), expectedDuration);
//                    assertEquals(dto.getScore(), expectedScore);
//                });
//    }
//
//
//}