package com.sportsmanagement.apigateway.presentationlayer.Athlete;

import com.sportsmanagement.apigateway.businesslayer.AthleteService;
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
@RequestMapping("api/v1/athletes")
public class AthletesController {

    private final Integer UUID_SIZE = 36;
    private AthleteService athleteService;

    public AthletesController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping(
            value = "/{athleteId}",
            produces = "application/json"
    )

    ResponseEntity<AthleteResponseModel> getAthleteById(
            @PathVariable String athleteId) {
        if(athleteId.length() != UUID_SIZE) {
            throw new InvalidInputException("Athlete id is invalid " + athleteId);
        }
        log.debug("1. Received in API-Gateway AthleteController getAthleteById with athleteId " + athleteId);
        AthleteResponseModel athleteResponseModel = athleteService.getAthleteById(athleteId);
        return ResponseEntity.ok().body(addLink(athleteResponseModel));
    }

    @GetMapping(
            produces = "application/json"
    )
    ResponseEntity<List<AthleteResponseModel>> getAllAthletes() {
        return ResponseEntity.ok().body(athleteService.getAllAthletes());
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<AthleteResponseModel> addAthlete(@RequestBody AthleteRequestModel athleteRequestModel) {
        AthleteResponseModel athleteResponseModel = athleteService.addAthlete(athleteRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addLink(athleteResponseModel));

    }

    @DeleteMapping(
            value = "/{athleteId}",
            produces = "application/json"
    )
    ResponseEntity<Void> deleteAthleteById(@PathVariable String athleteId) {
        if(athleteId.length() != UUID_SIZE) {
            throw new InvalidInputException("Athlete id is invalid " + athleteId);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(athleteService.deleteAthleteById(athleteId));
    }

    @PutMapping(
            value = "/{athleteId}",
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<Void> updateAthleteById(@RequestBody AthleteRequestModel athleteRequestModel, @PathVariable String athleteId) {
        if(athleteId.length() != UUID_SIZE) {
            throw new InvalidInputException("Athlete id is invalid " + athleteId);
        }
        return ResponseEntity.ok().body(athleteService.updateAthleteById(athleteRequestModel, athleteId));
    }

    private AthleteResponseModel addLink(AthleteResponseModel model) {

        Link selfLink = linkTo(methodOn(AthletesController.class)
                .getAthleteById(model.getAthleteId()))
                .withSelfRel();

        Link getAllLink = linkTo(methodOn(AthletesController.class)
                .getAllAthletes())
                .withRel("Get all athletes");

        Link postLink = linkTo(methodOn(AthletesController.class)
                .addAthlete(null))
                .withRel("Add athletes");

//        Link putLink = linkTo(methodOn(AthletesController.class)
//                .updateAthleteById(model.getAthleteId(), null))
//                .withRel("Update athlete");

        Link deleteLink = linkTo(methodOn(AthletesController.class)
                .deleteAthleteById(model.getAthleteId()))
                .withRel("Delete athlete");

    model.add(selfLink);
    model.add(getAllLink);
    model.add(postLink);
    model.add(deleteLink);

    return model;

    }

}
