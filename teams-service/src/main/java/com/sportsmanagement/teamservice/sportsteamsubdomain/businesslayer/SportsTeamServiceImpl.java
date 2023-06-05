package com.sportsmanagement.teamservice.sportsteamsubdomain.businesslayer;


import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportRepository;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportsLeague;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.Level;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.Team;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.TeamIdentifier;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.TeamRepository;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer.*;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.*;
import com.sportsmanagement.teamservice.sportsteamsubdomain.utils.exceptions.DuplicateIdentifierException;
import com.sportsmanagement.teamservice.sportsteamsubdomain.utils.exceptions.InvalidInputException;
import com.sportsmanagement.teamservice.sportsteamsubdomain.utils.exceptions.NotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SportsTeamServiceImpl implements SportsTeamService {

    private TeamRepository teamRepository;
    private TeamResponseMapper teamResponseMapper;
    private SportRepository sportRepository;
    private SportLeagueRequestMapper sportLeagueRequestMapper;
    private SportLeagueResponseMapper sportLeagueResponseMapper;
    private SportsTeamResponseMapper sportsTeamResponseMapper;
    private TeamRequestMapper teamRequestMapper;

    public SportsTeamServiceImpl(TeamRepository teamRepository, TeamResponseMapper teamResponseMapper, SportRepository sportRepository, SportLeagueRequestMapper sportLeagueRequestMapper, SportLeagueResponseMapper sportLeagueResponseMapper, SportsTeamResponseMapper sportsTeamResponseMapper, TeamRequestMapper teamRequestMapper) {
        this.teamRepository = teamRepository;
        this.teamResponseMapper = teamResponseMapper;
        this.sportRepository = sportRepository;
        this.sportLeagueRequestMapper = sportLeagueRequestMapper;
        this.sportLeagueResponseMapper = sportLeagueResponseMapper;
        this.sportsTeamResponseMapper = sportsTeamResponseMapper;
        this.teamResponseMapper = teamResponseMapper;
    }

    @Override
    public List<SportResponseModel> getSportsLeagues() {
        List<SportsLeague> sportsLeagues = sportRepository.findAll();
        return sportLeagueResponseMapper.entityListToResponseModelList(sportsLeagues);
    }

    @Override
    public SportsTeamResponseModel getSportsLeagueById(String sportId) {
        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);

        //check if it exists
        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        List<Team> teams = teamRepository.findAllBySportIdentifier_SportIdAndLevel(sportId, Level.COMPETITIVE);
        List<TeamResponseModel> teamResponseModels = teamResponseMapper.entityListToResponseModelList(teams);


        return sportsTeamResponseMapper.entitiesToResponseModels(sportsLeague, teamResponseModels);
    }

    @Override
    public SportResponseModel addSportsLeague(SportRequestModel sportRequestModel) {
        return sportLeagueResponseMapper.entityToResponseModel
                (sportRepository.save(sportLeagueRequestMapper.requestModelToEntity(sportRequestModel)));
    }

    @Override
    public SportResponseModel updateSportsLeague(SportRequestModel sportRequestModel, String sportId) {
        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);

        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        SportsLeague newSportsLeague = sportLeagueRequestMapper.requestModelToEntity(sportRequestModel);
        newSportsLeague.setId(sportsLeague.getId());
        newSportsLeague.setSportIdentifier(sportsLeague.getSportIdentifier());

        return sportLeagueResponseMapper.entityToResponseModel(sportRepository.save(newSportsLeague));
    }


    @Override
    public void removeSportsLeague(String sportId) {
        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);

        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        List<Team> events = teamRepository.findAllBySportIdentifier_SportId(sportId);

        teamRepository.deleteAll(events);


        if (events.isEmpty()){

        }
        sportRepository.delete(sportsLeague);

    }

    @Override
    public List<TeamResponseModel> getAllTeamsBySportId(String sportId) {

        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);

        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        return teamResponseMapper.entityListToResponseModelList(
                teamRepository.findAllBySportIdentifier_SportId(sportId));

    }

    @Override
    public List<TeamResponseModel> getTeamsInASportsLeagueByField(String sportId, Map<String, String> queryParams) {

        if (!sportRepository.existsBySportIdentifier_SportId(sportId)){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        //extract query params
        String level = queryParams.get("level");

        //convert to enum
        Map<String, Level> levelMap = new HashMap<>();
        levelMap.put("recreational", Level.RECREATIONAL);
        levelMap.put("intermediate", Level.INTERMEDIATE);
        levelMap.put("competitive", Level.COMPETITIVE);


        if (level != null) {
            return teamResponseMapper.entityListToResponseModelList(teamRepository.findAllBySportIdentifier_SportIdAndLevel(sportId, levelMap.get(level.toLowerCase())));
        }


        return teamResponseMapper.entityListToResponseModelList(
                teamRepository.findAllBySportIdentifier_SportId(sportId)
        );


    }

    @Override
    public TeamResponseModel getTeamInASportsLeagueByTeamId(String sportId, String teamId) {
        if (!sportRepository.existsBySportIdentifier_SportId(sportId)){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        Team foundTeam = teamRepository.findBySportIdentifier_SportIdAndTeamIdentifier_TeamId(sportId, teamId);

        return teamResponseMapper.entityToResponseModel(foundTeam);
    }


    @Override
    public TeamResponseModel addTeamToASportsLeague(TeamRequestModel teamRequestModel, String sportId) {

        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);

        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        TeamIdentifier teamIdentifier = new TeamIdentifier(teamRequestModel.getTeamId());


        try {
            return teamResponseMapper.entityToResponseModel
                    (teamRepository.save
                            (teamRequestMapper.responseModelToEntity(teamRequestModel, teamIdentifier, sportsLeague.getSportIdentifier())));
        } catch (DataAccessException ex) {
            if (ex.getMessage().contains("constrain[teamIdentifier]") ||
                    ex.getCause().toString().contains("ConstraintViolationException")) {
                throw new DuplicateIdentifierException("This sports league already has a team with identifier  " + teamRequestModel) {
                };
            }
            else throw new InvalidInputException(" Could not save team ");
        }

}

    @Override
    public TeamResponseModel updateTeamInSportsLeagueBySportId(TeamRequestModel teamRequestModel, String sportId, String teamId) {

        SportsLeague sportsLeague = sportRepository.findBySportIdentifier_SportId(sportId);
        if (sportsLeague == null){
            throw new NotFoundException("league with the following id does not exist: " + sportId);
        }

        Team ogTeam= teamRepository.findByTeamIdentifier_TeamId(teamId);

        if (ogTeam == null){
            throw new NotFoundException("team with the following id does not exist: " + teamId);
        }

        Team updatedTeam = teamRequestMapper.responseModelToEntity(teamRequestModel, ogTeam.getTeamIdentifier(), ogTeam.getSportIdentifier());

        updatedTeam .setId(ogTeam.getId());

        return teamResponseMapper.entityToResponseModel(teamRepository.save(updatedTeam));


    }

    @Override
    public void removeTeamInSportsLeague(String sportId, String teamId) {

        if (!sportRepository.existsBySportIdentifier_SportId(sportId)){
            return;
        }

        Team team = teamRepository.findByTeamIdentifier_TeamId(teamId);


        if (team == null){
            throw new NotFoundException("team with the following id does not exist: " + teamId);
        }

        teamRepository.delete(team);
    }


}
