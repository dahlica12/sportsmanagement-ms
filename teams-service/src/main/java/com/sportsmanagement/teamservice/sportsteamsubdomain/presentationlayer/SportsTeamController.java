package com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer;


import com.sportsmanagement.teamservice.sportsteamsubdomain.businesslayer.SportsTeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sports")
public class SportsTeamController {

    private SportsTeamService sportsTeamService;

    public SportsTeamController(SportsTeamService sportsTeamService) {
        this.sportsTeamService = sportsTeamService;
    }

    @GetMapping()
    List<SportResponseModel> getAllSportsLeagues(){
        return sportsTeamService.getSportsLeagues();
    }


    @GetMapping("/{sportId}")
    SportsTeamResponseModel getSportsLeagueById(@PathVariable String sportId) {
        return sportsTeamService.getSportsLeagueById(sportId);
    }

    @PostMapping()
    SportResponseModel addSportsLeague(@RequestBody SportRequestModel sportRequestModel){
        return sportsTeamService.addSportsLeague(sportRequestModel);
    }

    @PutMapping("/{sportId}")
    SportResponseModel updateSportLeagueDetails(@RequestBody SportRequestModel sportRequestModel, @PathVariable String sportId){
        return sportsTeamService.updateSportsLeague(sportRequestModel, sportId);
    }

    @DeleteMapping("/{sportId}")
    void deleteSportsLeague(@PathVariable String sportId){
        sportsTeamService.removeSportsLeague(sportId);
    }

    @GetMapping("/{sportId}/teams")
    public List<TeamResponseModel> getAllTeamsBySportId(@PathVariable String sportId) {
        return sportsTeamService.getAllTeamsBySportId(sportId);
    }

    @GetMapping("/{sportId}/teams/{teamId}")
    public TeamResponseModel getTeamInASportsLeagueByTeamId(@PathVariable String sportId, @PathVariable String teamId) {
        return sportsTeamService.getTeamInASportsLeagueByTeamId(sportId, teamId);
    }

    @PostMapping("/{sportId}")
    public TeamResponseModel saveTeamInASportsLeague(@RequestBody TeamRequestModel teamRequestModel, @PathVariable String sportId) {
        return sportsTeamService.addTeamToASportsLeague(teamRequestModel, sportId);
    }

    @PutMapping("/{sportId}/teams/{teamId}")
    public TeamResponseModel updateTeamInASportsLeague(@PathVariable String sportId, @PathVariable String teamId, @RequestBody TeamRequestModel teamRequestModel) {
        return sportsTeamService.updateTeamInSportsLeagueBySportId(teamRequestModel, sportId, teamId);
    }

    @DeleteMapping("/{sportId}/teams/teamId")
    public void removeTeamInASportsLeague(@PathVariable String sportId, @PathVariable String teamId) {
        sportsTeamService.removeTeamInSportsLeague(sportId, teamId);
    }

}
