package com.sportsmanagement.teamservice.sportsteamsubdomain.businesslayer;



import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.Team;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.*;

import java.util.List;
import java.util.Map;

public interface SportsTeamService {

    //for sports leagues
    List<SportResponseModel> getSportsLeagues();
    SportsTeamResponseModel getSportsLeagueById(String sportId);
    SportResponseModel addSportsLeague(SportRequestModel sportRequestModel);
    SportResponseModel updateSportsLeague(SportRequestModel sportRequestModel, String sportId);
    void removeSportsLeague(String sportId);

    List<TeamResponseModel> getAllTeamsBySportId(String sportId);
    List<TeamResponseModel> getTeamsInASportsLeagueByField(String sportId, Map<String, String> queryParams);
    TeamResponseModel getTeamInASportsLeagueByTeamId(String sportId, String teamId);
    TeamResponseModel addTeamToASportsLeague(TeamRequestModel teamRequestModel, String sportId);
    TeamResponseModel updateTeamInSportsLeagueBySportId(TeamRequestModel teamRequestModel, String sportId, String teamId);
    void removeTeamInSportsLeague(String sportId, String teamId);
}
