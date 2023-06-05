package com.sportsmanagement.athleteservice.athletemanagementsubdomain.businesslayer;

import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Athlete;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.AthleteIdentifier;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer.AthleteRequestModel;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer.AthleteResponseModel;

import java.util.List;


public interface AthleteService {

   List<AthleteResponseModel> getAthletes();
   Athlete getAthleteByAthleteId(String athleteId);
   AthleteResponseModel saveAthlete(AthleteRequestModel athleteRequestModel);

   Athlete updateAthlete(Athlete athlete, String athleteId);
   void removeAthlete(String athleteId);
   //List<Athlete> findAthletesByField (Map<String, String> queryParams);

}
