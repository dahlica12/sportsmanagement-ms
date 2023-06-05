package com.sportsmanagement.apigateway.presentationlayer.Coach;

import com.sportsmanagement.apigateway.businesslayer.CoachService;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachesController;
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
@RequestMapping("api/v1/coaches")
public class CoachesController {

    private final Integer UUID_SIZE = 32;
    private CoachService coachService;

    public CoachesController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping(
            value = "/{coachId}",
            produces = "application/json"
    )
    ResponseEntity<CoachResponseModel> getCoachById(@PathVariable String coachId) {
        if (coachId.length() != UUID_SIZE) {
            throw new InvalidInputException("Coach id is invalid " + coachId);
        }
        log.debug("1. Received in API-Gateway CoachController getCoachById with coachId " + coachId);
        return ResponseEntity.ok().body(coachService.getCoachById(coachId));
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<List<CoachResponseModel>> getAllCoaches() {
        return ResponseEntity.ok().body(coachService.getAllCoaches());
    }

    @PostMapping(
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<CoachResponseModel> addCoach(@RequestBody CoachRequestModel coachRequestModel) {
        CoachResponseModel CoachResponseModel = coachService.addCoach(coachRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(addLink(CoachResponseModel));

    }

    @DeleteMapping(
            value = "/{coachId}",
            produces = "application/json"
    )
    ResponseEntity<Void> deleteCoachById(@PathVariable String coachId) {
        if(coachId.length() != UUID_SIZE) {
            throw new InvalidInputException("Coach id is invalid " + coachId);
        }
        coachService.deleteCoachById(coachId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(coachService.deleteCoachById(coachId));
    }

    @PutMapping(
            value = "/{coachId}",
            consumes = "application/json",
            produces = "application/json"
    )
    ResponseEntity<Void> updateCoachById(@RequestBody CoachRequestModel coachRequestModel, @PathVariable String coachId) {
        if(coachId.length() != UUID_SIZE) {
            throw new InvalidInputException("Coach id is invalid " + coachId);
        }
        return ResponseEntity.ok().body(coachService.updateCoachById(coachRequestModel, coachId));
    }

    private CoachResponseModel addLink(CoachResponseModel model) {

        Link selfLink = linkTo(methodOn(CoachesController.class)
                .getCoachById(model.getCoachId()))
                .withSelfRel();

        Link getAllLink = linkTo(methodOn(CoachesController.class)
                .getAllCoaches())
                .withRel("Get all coaches");

        Link postLink = linkTo(methodOn(CoachesController.class)
                .addCoach(null))
                .withRel("Add coaches");

//        Link putLink = linkTo(methodOn(CoachsController.class)
//                .updateCoachById(model.getcoachId(), null))
//                .withRel("Update Coach");

        Link deleteLink = linkTo(methodOn(CoachesController.class)
                .deleteCoachById(model.getCoachId()))
                .withRel("Delete Coach");

        model.add(selfLink);
        model.add(getAllLink);
        model.add(postLink);
        model.add(deleteLink);

        return model;

    }
}
