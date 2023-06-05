package com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer;

import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.CoachRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //after each destroy app method
public class CoachControllerIntegrationTest {

    private final String BASE_URI_COACHES = "/api/v1/coaches";
    private final String VALID_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389";
    private final String VALID_COACH_TITLE = "Surveyor";

    private String INVALID_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389nog";

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CoachRepository coachRepository;

    //clean up reset database after each test
    @AfterEach
    void tearDown() {
        coachRepository.deleteAll();
    }

    //get all
    @Sql({"/data-mysql.sql"})
    @Test
    public void whenCoachesExist_thenReturnAllCoaches() {
        Integer expectedNumCoaches = 6;

        webTestClient.get()
                .uri(BASE_URI_COACHES)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.length()").isEqualTo(expectedNumCoaches);
    }

    //post coach

    @Test
    public void whenCreateCoachWithValidValues_thenReturnNewCoach() {
       //arrange
        String expectedTeamId = "123sport";
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        String expectedEmail = "JohnDoe@gmail.com";
        String expectedPhoneNumber = "514-999-9999";
        Double expectedSalary = 12000.00;
        String expectedTitle = "manager";

        CoachRequestModel coachRequestModel = new CoachRequestModel(expectedTeamId, expectedFirstName, expectedLastName, expectedEmail, expectedPhoneNumber, expectedSalary, expectedTitle);

    //act and assert
        webTestClient.post()
                .uri(BASE_URI_COACHES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(coachRequestModel)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(CoachResponseModel.class)
                .value((dto) -> {
                    assertNotNull(dto);
                    assertNotNull(dto.getCoachId());
                    assertEquals(dto.getTeamId(), expectedTeamId);
                    assertEquals(dto.getFirstName(), expectedFirstName);
                    assertEquals(dto.getLastName(), expectedLastName);
                    assertEquals(dto.getEmailAddress(), expectedEmail);
                    assertEquals(dto.getPhoneNumber(), expectedPhoneNumber);
                    assertEquals(dto.getSalary(), expectedSalary);
                    assertEquals(dto.getTitle(), expectedTitle);
                });
    }

//    @Test
//    public void whenGetAllCoaches_thenReturnAllCoaches() {
//        //arrange
//        int expectedNumCoaches = 6;
//
//        //act and assert
//        webTestClient.get()
//                .uri(BASE_URI_COACHES)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBodyList(CoachResponseModel.class)
//                .hasSize(expectedNumCoaches);
//    }
//
//    @Test
//    public void whenCreateCoachWithInvalidValues_thenReturnBadRequest() {
//        //arrange
//        //arrange
//        String invalidTeamId = "volley-sport-invalid";
//        String invalidFirstName = "Road";
//        String invalidLastName = "invalid";
//        String invalidEmail = "invalid";
//        String invalidPhoneNumber = "invalid";
//        Double invalidSalary = 0.0;
//        String invalidTitle = "invalid";
//        CoachRequestModel invalidCoach = new CoachRequestModel(invalidTeamId, invalidFirstName, invalidLastName, invalidEmail, invalidPhoneNumber, invalidSalary, invalidTitle);
//
//        //act and assert
//        webTestClient.post()
//                .uri(BASE_URI_COACHES)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidCoach)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isBadRequest();
//    }
//
//    @Test
//    public void whenGetCoachById_thenReturnCoach() {
//        //arrange
//        String coachId = "c1e6624e-0e41-4e6e-8eab-4b3bdc679f72";
//
//        //act and assert
//        webTestClient.get()
//                .uri(BASE_URI_COACHES + "/" + coachId)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(CoachResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertEquals(dto.getCoachId(), coachId);
//                });
//    }
//
//    @Test
//    public void whenGetCoachByInvalidId_thenReturnNotFound() {
//        //arrange
//        String invalidCoachId = "invalid-coach-id";
//
//        //act and assert
//        webTestClient.get()
//                .uri(BASE_URI_COACHES + "/" + invalidCoachId)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isNotFound();
//    }
//
//    @Test
//    public void whenUpdateCoach_thenReturnUpdatedCoach() {
//        //arrange
//
//        String expectedTeamId = "123sport";
//        String expectedFirstName = "John";
//        String expectedLastName = "Doe";
//        String expectedEmail = "JohnDoe@gmail.com";
//        String expectedPhoneNumber = "514-999-9999";
//        Double expectedSalary = 12000.00;
//        String expectedTitle = "manager";
//
//
//        CoachRequestModel updatedCoach = new CoachRequestModel(expectedTeamId, expectedFirstName, expectedLastName, expectedEmail, expectedPhoneNumber, expectedSalary, expectedTitle);
//        updatedCoach.setFirstName("UpdatedFirstName");
//
//        //act and assert
//        webTestClient.put()
//                .uri(BASE_URI_COACHES + "/" + VALID_COACH_ID)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(updatedCoach)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().isOk()
//                .expectHeader().contentType(MediaType.APPLICATION_JSON)
//                .expectBody(CoachResponseModel.class)
//                .value((dto) -> {
//                    assertNotNull(dto);
//                    assertEquals(dto.getCoachId(), VALID_COACH_ID);
//                    assertEquals(dto.getFirstName(), updatedCoach.getFirstName());
//                });
//    }

//    @Test
//    public void whenDeleteCoach_thenCoachDeleted() {
//        //arrange
//
//        //act and assert
//        webTestClient.delete()
//                .uri(BASE_URI_COACHES + "/" + VALID_COACH_ID)
//                .exchange()
//                .expectStatus().isOk();
//    }



};