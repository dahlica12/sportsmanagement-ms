package com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer;

import com.sportsmanagement.coachservice.coachmanagementsubdomain.businesslayer.CoachService;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.Coach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/coaches")
public class CoachController {

    private CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping()
    public ResponseEntity<List<CoachResponseModel>> getCoaches(){
        return ResponseEntity.ok().body(coachService.getCoaches());
    }

    @GetMapping("/{coachId}")
    public ResponseEntity<CoachResponseModel> getCoachByCoachId(@PathVariable String coachId){
        return ResponseEntity.ok().body(coachService.getCoachByCoachId(coachId));
    }

    @PostMapping()
    public ResponseEntity<CoachResponseModel> saveCoach(@RequestBody CoachRequestModel coachRequestModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(coachService.saveCoach(coachRequestModel));
    }

    @PutMapping("/{coachId}")
    public ResponseEntity<CoachResponseModel> updateCoach(@RequestBody CoachRequestModel coachRequestModel, @PathVariable String coachId){
        return ResponseEntity.ok().body(coachService.updateCoach(coachRequestModel, coachId));
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> removeCoach(@PathVariable String coachId){
        coachService.removeCoach(coachId);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
