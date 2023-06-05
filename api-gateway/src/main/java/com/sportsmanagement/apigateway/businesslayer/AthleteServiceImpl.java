package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.AthleteServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.Stats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AthleteServiceImpl implements AthleteService {

    AthleteServiceClient athleteServiceClient;


    public AthleteServiceImpl(AthleteServiceClient athleteServiceClient) {
        this.athleteServiceClient = athleteServiceClient;
    }

    @Override
    public AthleteResponseModel getAthleteById(String athleteId) {
        log.debug("2. Received in API-Gateway AthleteServiceImpl getAthleteAggregate with athleteId: " + athleteId);
        return athleteServiceClient.getAthleteById(athleteId);
    }

    @Override
    public List<AthleteResponseModel> getAllAthletes() {

        return athleteServiceClient.getAllAthletes();
    }

    @Override
    public Void deleteAthleteById(String athleteId) {

        return athleteServiceClient.deleteAthleteById(athleteId);

    }

    @Override
    public AthleteResponseModel addAthlete(AthleteRequestModel athleteRequestModel) {
        Stats stats = new Stats(athleteRequestModel.getPointsWon(),athleteRequestModel.getGamesWon(),athleteRequestModel.getGamesLost());

        return athleteServiceClient.addAthlete(athleteRequestModel, stats);

    }

    @Override
    public Void updateAthleteById(AthleteRequestModel athleteRequestModel, String athleteId) {

        return athleteServiceClient.updateAthleteById(athleteRequestModel, athleteId);


    }


}
