package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.CoachServiceClient;
import com.sportsmanagement.apigateway.domainclientlayer.SportServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.SportResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.SportsTeamResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.TeamResponseModel;
import com.sportsmanagement.apigateway.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SportsTeamServiceImplUnitTest {

    @Autowired
    SportsTeamService sportsTeamService;

    @MockBean
    SportServiceClient sportServiceClient;

    private final String VALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3";
    private final String INVALID_SPORT_ID = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3tv7lwrong";


    @Test
    void whenASportIsValid_ShouldReturnSportId() {

        //arrange
        //String sportId = "514c3c6f-8e4e-47c7-9d93-e7d16da9f7a3";
        SportsTeamResponseModel sport = new SportsTeamResponseModel
                (VALID_SPORT_ID, "volleyball");


        when(sportServiceClient.getSportAggregate(VALID_SPORT_ID))
                .thenReturn(sport);

        //act
        SportsTeamResponseModel realSport = sportsTeamService.getSportAggregate(VALID_SPORT_ID);

        //assert
        assertNotNull(realSport);
        assertEquals(sport, realSport);
    }
    @Test
    void whenASportIsInvalid_ShouldReturnNull(){

        SportsTeamResponseModel sport = new SportsTeamResponseModel
                (INVALID_SPORT_ID, "volleyball");

        when(sportServiceClient.getSportAggregate(INVALID_SPORT_ID))
                .thenReturn(null);

        SportsTeamResponseModel realSport = sportsTeamService.getSportAggregate(VALID_SPORT_ID);

        assertNotEquals(sport, realSport);
    }

//    @Test
//    void whenInvalidSportId_OnGetSportById_thenThrow_NotFoundException() throws NotFoundException {
//
//
//        when(sportServiceClient.getSportAggregate(INVALID_SPORT_ID))
//                .thenReturn(null);
//
//        assertThrows(NotFoundException.class, () -> {
//            sportsTeamService.getSportAggregate(invalidSportId);
//        });


    //}
}