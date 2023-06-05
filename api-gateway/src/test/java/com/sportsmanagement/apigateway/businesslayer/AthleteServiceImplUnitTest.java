package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.AthleteServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.Stats;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AthleteServiceImplUnitTest {

    @Autowired
    AthleteService athleteService;

    @MockBean
    AthleteServiceClient athleteServiceClient;

    private final String VALID_ATHLETE_ID = "0f5864b4-203b-4932-9e28-65df721b0793";
    private final String INVALID_ATHLETE_ID = "0f5864b4-203b-4932-9e28-65df721b0793wrong";


    @Test
    void whenAthleteIsValid_ShouldReturnAthleteId() {

        //arrange
        AthleteResponseModel athlete = new AthleteResponseModel(
                VALID_ATHLETE_ID, "123sport", "Stanwood", "Mathet", "smathet0@marketwatch.com",
                "basketball", 70.3, 164.2, 20,
                "Male", Status.AVAILABLE,1,2,3);

        when(athleteServiceClient.getAthleteById(VALID_ATHLETE_ID))
                .thenReturn(athlete);

        //act
        AthleteResponseModel ogAthlete = athleteService.getAthleteById(VALID_ATHLETE_ID);


        //assert
        assertNotNull(ogAthlete);
        assertEquals(athlete, ogAthlete);

    }

    @Test
    void whenAthleteIsInvalid_ShouldReturnNull(){

    }

//    @Test
//    void whenInvalidAthleteId_OnGetAthleteById_thenThrow_NotFoundException() {
//
//        String invalidAthleteId = "123";
//
//        when(athleteServiceClient.getAthleteById(invalidAthleteId))
//                .thenReturn(null);
//
//        assertThrows(NotFoundException.class, () -> {
//            athleteService.getAthleteById(invalidAthleteId);
//        });
//    }


}