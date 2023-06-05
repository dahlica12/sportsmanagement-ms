package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.*;

import java.util.List;

public interface SportsTeamService {

    SportsTeamResponseModel getSportAggregate(String sportId);
    List<SportsTeamResponseModel> getAllSportsLeagues();
    SportResponseModel addSportsLeague(SportRequestModel sportRequestModel);
    //SportResponseModel updateSportsLeague(SportRequestModel sportRequestModel, String sportId);
    Void removeSportsLeague(String sportId);

    List<TeamResponseModel> getAllTeamsBySportId(String sportId);
    //TeamResponseModel getTeamInASportsLeagueByTeamId(String sportId, String teamId);
    TeamResponseModel addTeamToASportsLeague(TeamRequestModel teamRequestModel, String sportId);
    //TeamResponseModel updateTeamInSportsLeagueBySportId(TeamRequestModel teamRequestModel, String sportId, String teamId);
    Void removeTeamInSportsLeague(String sportId, String teamId);
}
