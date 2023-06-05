package com.sportsmanagement.coachservice.coachmanagementsubdomain.businesslayer;

import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.Coach;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.CoachRepository;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.datamapperlayer.CoachRequestMapper;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.datamapperlayer.CoachResponseMapper;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer.CoachRequestModel;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer.CoachResponseModel;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachServiceImpl implements CoachService{

    private CoachRepository coachRepository;
    private CoachResponseMapper coachResponseMapper;
    private CoachRequestMapper coachRequestMapper;

    public CoachServiceImpl(CoachRepository coachRepository, CoachResponseMapper coachResponseMapper, CoachRequestMapper coachRequestMapper) {
        this.coachRepository = coachRepository;
        this.coachResponseMapper = coachResponseMapper;
        this.coachRequestMapper = coachRequestMapper;
    }

    @Override
    public List<CoachResponseModel> getCoaches() {
        return coachResponseMapper.entityListToResponseModelList(coachRepository.findAll());
    }

    @Override
    public CoachResponseModel getCoachByCoachId(String coachId) {
        return coachResponseMapper.entityToResponseModel(coachRepository.findByCoachIdentifier_CoachId(coachId));
    }

    @Override
    public CoachResponseModel saveCoach(CoachRequestModel coachRequestModel) {
        return coachResponseMapper.entityToResponseModel(coachRepository.save(coachRequestMapper.requestModelToEntity(coachRequestModel)));
    }



    @Override
    public CoachResponseModel updateCoach(CoachRequestModel coachRequestModel, String coachId) {
        Coach existingCoach = coachRepository.findByCoachIdentifier_CoachId(coachId);

        if (existingCoach == null){
            throw new NotFoundException("Coach with the following id does not exist: " + coachId);
        }

        Coach coach = coachRequestMapper.requestModelToEntity(coachRequestModel);
        coach.setId(existingCoach.getId());
        coach.setCoachIdentifier(existingCoach.getCoachIdentifier());

        return coachResponseMapper.entityToResponseModel(coachRepository.save(coach));
    }

    @Override
    public void removeCoach(String coachId) {

        Coach existingCoach = coachRepository.findByCoachIdentifier_CoachId(coachId);

        if (existingCoach == null){
            throw new NotFoundException("Coach with the following id does not exist: " + coachId);
        }

        coachRepository.delete(existingCoach);
    }
}
