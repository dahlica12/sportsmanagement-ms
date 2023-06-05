package com.sportsmanagement.apigateway.businesslayer;


import com.sportsmanagement.apigateway.domainclientlayer.CoachServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
//import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CoachServiceImplUnitTest {

    @Autowired
    CoachService coachService;


    @MockBean
    CoachServiceClient coachServiceClient;


    private final String VALID_COACH_ID = "b15c35ae-c3c1-4c2c-ac5d-0a63277e9389";
    @Test
    void whenACoachIsValid_ShouldReturnCoachId() {

        //arrange

        CoachResponseModel testCoach = new CoachResponseModel(
                VALID_COACH_ID, "123sport", "John", "Doe",
                "JohnDoe@gmail.com", "514-999-9999",
                12000.00, "manager");



        when(coachServiceClient.getCoachById(VALID_COACH_ID))
                .thenReturn(testCoach);

        //act
        CoachResponseModel realCoach = coachService.getCoachById(VALID_COACH_ID);

        //assert
        assertNotNull(realCoach);
        assertEquals(testCoach, realCoach);
    }

//    @Test
//    void whenInvalidCoachId_OnGetCoachById_thenThrow_NotFoundException() {
//
//        String invalidCoachId = "425467";
//
//        when(coachServiceClient.getCoachById(invalidCoachId))
//                .thenReturn(null);
//
//        assertThrows(NotFoundException.class, () -> {
//            coachService.getCoachById(invalidCoachId);
//        });


   // }
}