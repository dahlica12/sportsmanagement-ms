package com.sportsmanagement.coachservice.coachmanagementsubdomain.businesslayer;

import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.Coach;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer.CoachRequestModel;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer.CoachResponseModel;

import java.util.List;

public interface CoachService {

    List<CoachResponseModel> getCoaches();
    CoachResponseModel getCoachByCoachId(String coachId);
    CoachResponseModel saveCoach(CoachRequestModel coachRequestModel);
    CoachResponseModel updateCoach(CoachRequestModel coachRequestModel, String coachId);
    void removeCoach(String coachId);
}
