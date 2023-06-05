package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteResponseModel;

import java.util.List;

public interface AthleteService {

    AthleteResponseModel getAthleteById(String athleteId);
    List<AthleteResponseModel> getAllAthletes();
    Void deleteAthleteById(String athleteId);
    AthleteResponseModel addAthlete(AthleteRequestModel athleteRequestModel);
    Void updateAthleteById(AthleteRequestModel athleteRequestModel, String athleteId);
}
