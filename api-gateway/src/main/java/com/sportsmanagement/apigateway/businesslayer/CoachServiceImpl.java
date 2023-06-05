package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.CoachServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CoachServiceImpl implements CoachService{

    CoachServiceClient coachServiceClient;

    public CoachServiceImpl(CoachServiceClient coachServiceClient){
        this.coachServiceClient = coachServiceClient;
    }

    @Override
    public CoachResponseModel getCoachById(String coachId) {
        log.debug("2. Received in API-Gateway CoachServiceImpl getCoachById with coachId: " + coachId);
        return coachServiceClient.getCoachById(coachId);
    }

    @Override
    public List<CoachResponseModel> getAllCoaches() {
        return coachServiceClient.getAllCoaches();
    }

    @Override
    public Void deleteCoachById(String CoachId) {

        return coachServiceClient.deleteCoachById(CoachId);

    }

    @Override
    public CoachResponseModel addCoach(CoachRequestModel CoachRequestModel) {

        return coachServiceClient.addCoach(CoachRequestModel);

    }

    @Override
    public Void updateCoachById(CoachRequestModel CoachRequestModel, String CoachId) {

        return coachServiceClient.updateCoachById(CoachRequestModel, CoachId);


    }
}
