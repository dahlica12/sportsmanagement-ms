package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;

import java.util.List;

public interface CoachService {

    CoachResponseModel getCoachById(String coachId);
    List<CoachResponseModel> getAllCoaches();
    Void deleteCoachById(String CoachId);
    CoachResponseModel addCoach(CoachRequestModel CoachRequestModel);
    Void updateCoachById(CoachRequestModel CoachRequestModel, String CoachId);
}
