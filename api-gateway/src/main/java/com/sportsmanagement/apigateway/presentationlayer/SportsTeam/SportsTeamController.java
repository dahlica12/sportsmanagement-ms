package com.sportsmanagement.apigateway.presentationlayer.SportsTeam;

import com.sportsmanagement.apigateway.businesslayer.SportsTeamService;
import com.sportsmanagement.apigateway.utils.exceptions.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("api/v1/sports")
public class SportsTeamController {

    private final Integer UUID_SIZE = 32;
    private final Integer maxIdSize = 10;
    private SportsTeamService sportsTeamService;

    public SportsTeamController(SportsTeamService sportsTeamService) {
        this.sportsTeamService = sportsTeamService;
    }

    @GetMapping(
            value = "/{sportId}",
            produces = "application/json"
    )
    ResponseEntity<SportsTeamResponseModel> getSportsTeamAggregate(
            @PathVariable String sportId) {
        if (sportId.length() < maxIdSize) {
            throw new InvalidInputException("Sport id is invalid " + sportId);
        }
        log.debug("1. Received in API-Gateway Sports Controller getSportTeamAggregate with sportId " + sportId);
        return ResponseEntity.ok().body(sportsTeamService.getSportAggregate(sportId));
    }

    @GetMapping(
            produces = "application/json"
    )
    ResponseEntity<List<SportsTeamResponseModel>> getAllSportsLeague() {
        return ResponseEntity.ok().body(sportsTeamService.getAllSportsLeagues());
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<SportResponseModel> addSport(@RequestBody SportRequestModel sportRequestModel) {
       SportResponseModel sportResponseModel = sportsTeamService.addSportsLeague(sportRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addLink(sportResponseModel));

    }

    @DeleteMapping(
            value = "/{sportId}",
            produces = "application/json"
    )
    ResponseEntity<Void> deleteSportsLeagueById(@PathVariable String sportId) {
        if(sportId.length() != UUID_SIZE) {
            throw new InvalidInputException("Sport id is invalid " + sportId);
        }
       //sportsTeamService.removeSportsLeague(sportId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sportsTeamService.removeSportsLeague(sportId));
    }

    @GetMapping(
            value = "/{sportId}/teams",
            produces = "application/json"
    )
    ResponseEntity<List<TeamResponseModel>> getAllTeamsBySportId(@PathVariable String sportId) {
        return ResponseEntity.ok().body(sportsTeamService.getAllTeamsBySportId(sportId));
    }

    @PostMapping(
            value = "/{sportId}/teams",
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<TeamResponseModel> addTeamToASportsLeague(@RequestBody TeamRequestModel teamRequestModel, @PathVariable String sportId) {
        //TeamResponseModel  teamResponseModel = sportsTeamService.addTeamToASportsLeague(teamRequestModel, sportId);
        return ResponseEntity.status(HttpStatus.CREATED).body(sportsTeamService.addTeamToASportsLeague(teamRequestModel, sportId));

    }

    @DeleteMapping(
            value = "/{sportId}/teams/{teamId}",
            produces = "application/json"
    )
    ResponseEntity<Void> removeTeamInSportsLeague(@PathVariable String sportId, @PathVariable String teamId) {
        if(sportId.length() != UUID_SIZE) {
            throw new InvalidInputException("Sport id is invalid " + sportId);
        }

        if(teamId.length() != UUID_SIZE) {
            throw new InvalidInputException("Team id is invalid " + teamId);
        }
        //sportsTeamService.removeTeamInSportsLeague(sportId, teamId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(sportsTeamService.removeTeamInSportsLeague(sportId, teamId));
    }


    private SportResponseModel addLink(SportResponseModel model) {

        Link selfLink = linkTo(methodOn(SportsTeamController.class)
                .getAllTeamsBySportId(model.getSportId()))
                .withSelfRel();

        Link getAllLink = linkTo(methodOn(SportsTeamController.class)
                .getAllSportsLeague())
                .withRel("Get all sports leagues");

        Link postLink = linkTo(methodOn(SportsTeamController.class)
                .addSport(null))
                .withRel("Add sport");

        Link deleteLink = linkTo(methodOn(SportsTeamController.class)
                .deleteSportsLeagueById(model.getSportId()))
                .withRel("Delete sport");

        Link getAllLinkTeam = linkTo(methodOn(SportsTeamController.class)
                .getAllTeamsBySportId(model.getSportId()))
                .withRel("Get all sports leagues");

//        Link postLinkTeam = linkTo(methodOn(SportsTeamController.class)
//                .addTeamToASportsLeague(null, model.getSportId()))
//                .withRel("Add team");



        model.add(selfLink);
        model.add(getAllLink);
        model.add(postLink);
        model.add(deleteLink);
        model.add(getAllLinkTeam);
      //  model.add(postLinkTeam);

        return model;

    }


}
