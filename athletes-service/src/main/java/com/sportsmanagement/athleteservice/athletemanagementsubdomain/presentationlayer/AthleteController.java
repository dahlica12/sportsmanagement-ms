package com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer;

import com.sportsmanagement.athleteservice.athletemanagementsubdomain.businesslayer.AthleteService;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Athlete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/athletes")
public class AthleteController {

    private AthleteService athleteService;


    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    //@GetMapping
    //public ResponseEntity<List<Athlete>> getAthletes() {
      //  return new ResponseEntity<List<Athlete>>(athleteService.getAthletes(), HttpStatus.OK) ;


    @GetMapping
    public ResponseEntity<List<AthleteResponseModel>> getAthletes() {
        return ResponseEntity.ok().body(athleteService.getAthletes());
    }

    @GetMapping("/{athleteId}")
    public ResponseEntity<Athlete> getAthleteByAthleteId(@PathVariable String athleteId){
        return ResponseEntity.ok().body(athleteService.getAthleteByAthleteId(athleteId));
    }

    @PostMapping()
    public ResponseEntity<AthleteResponseModel> saveAthlete(@RequestBody AthleteRequestModel athleteRequestModel) {
      //if


       return ResponseEntity.status(HttpStatus.CREATED).body(athleteService.saveAthlete(athleteRequestModel));

       //return response entity .status.http.created.body

    }


    @PutMapping("/{athleteId}")
    public ResponseEntity<Athlete> updateAthlete(@RequestBody Athlete athlete, @PathVariable String athleteId) {
        return ResponseEntity.ok().body(athleteService.updateAthlete(athlete, athleteId));

        //response entity.ok.body
    }

    @DeleteMapping("/{athleteId}")
    public ResponseEntity<Void> removeAthlete(@PathVariable String athleteId){
        athleteService.removeAthlete(athleteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
