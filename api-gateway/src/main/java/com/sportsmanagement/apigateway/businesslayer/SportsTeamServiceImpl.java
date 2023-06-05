package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.SportServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SportsTeamServiceImpl implements SportsTeamService {

    SportServiceClient sportServiceClient;

    public SportsTeamServiceImpl(SportServiceClient sportServiceClient) {
        this.sportServiceClient = sportServiceClient;
    }

    @Override
    public SportsTeamResponseModel getSportAggregate(String sportId) {
        log.debug("2. Received in API-Gateway STeamsServiceImpl getSportsTeamAggregate with sportId: " + sportId);
        return sportServiceClient.getSportAggregate(sportId);
    }

    @Override
    public List<SportsTeamResponseModel> getAllSportsLeagues() {

        return sportServiceClient.getAllSportsLeagues();
    }


    @Override
    public SportResponseModel addSportsLeague(SportRequestModel sportRequestModel) {
        return sportServiceClient.addSport(sportRequestModel);
    }

    @Override
    public Void removeSportsLeague(String sportId) {
        sportServiceClient.deleteSportById(sportId);
        return null;
    }

    @Override
    public List<TeamResponseModel> getAllTeamsBySportId(String sportId) {
        return sportServiceClient.getAllTeamsBySportId(sportId);
    }

    @Override
    public TeamResponseModel addTeamToASportsLeague(TeamRequestModel teamRequestModel, String sportId) {
        return sportServiceClient.addTeamToASportsLeague(teamRequestModel, sportId);
    }

    @Override
    public Void removeTeamInSportsLeague(String sportId, String teamId) {
        sportServiceClient.removeTeamInSportsLeague(sportId, teamId);
        return null;
    }
}
