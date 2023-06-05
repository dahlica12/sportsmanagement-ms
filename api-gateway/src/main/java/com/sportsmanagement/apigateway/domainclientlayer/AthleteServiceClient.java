package com.sportsmanagement.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.Stats;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.utils.HttpErrorInfo;
import com.sportsmanagement.apigateway.utils.exceptions.InvalidInputException;
import com.sportsmanagement.apigateway.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class AthleteServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String ATHLETE_SERVICE_BASE_URL;


    public AthleteServiceClient(RestTemplate restTemplate,
                                ObjectMapper mapper,
                                @Value("${app.athletes-service.host}") String athletesServiceHost,
                                @Value("${app.athletes-service.port}") String athletesServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        ATHLETE_SERVICE_BASE_URL = "http://" + athletesServiceHost + ":" + athletesServicePort +
                "/api/v1/athletes";
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();
        }

    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
//include all possible responses from the athlete
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }

    public AthleteResponseModel getAthleteById(String athleteId) {
        log.debug("3. Received in API-Gateway AthleteServiceClient getAthleteById with athleteId: " + athleteId);

        try {
            String url = ATHLETE_SERVICE_BASE_URL + "/" + athleteId;

            AthleteResponseModel athleteResponseModel = restTemplate.getForObject(url, AthleteResponseModel.class);

            log.debug("5. Received in API-Gateway AthleteServiceClient getAthleteAggregate with AthleteResponseModel: "
                    + athleteResponseModel.getAthleteId());

            return athleteResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway AthleteServiceClient getAthleteById exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public List<AthleteResponseModel> getAllAthletes() {

        try {
            String url = ATHLETE_SERVICE_BASE_URL;

            ResponseEntity<List<AthleteResponseModel>> responseEntity   = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<AthleteResponseModel>>() {
                    });

            List<AthleteResponseModel> athletes = responseEntity.getBody();
            List<AthleteResponseModel> athlete_final = new ArrayList<AthleteResponseModel>();
            for (AthleteResponseModel athlete: athletes){
                AthleteResponseModel temp_athlete = new AthleteResponseModel(athlete.getAthleteId(),athlete.getTeamId(),athlete.getFirstName(),
                        athlete.getLastName(),athlete.getEmailAddress(),athlete.getSportName(),athlete.getHeight(),
                        athlete.getWeight(),athlete.getAge(),athlete.getGender(),athlete.getStatus(), athlete.getPointsWon(),athlete.getGamesWon(),athlete.getGamesLost());
                athlete_final.add(temp_athlete);
            }

            return athlete_final;
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in api-gateway athleteserviceclient getAllAthletes exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void deleteAthleteById(String athleteId) {

        try {
            String url = ATHLETE_SERVICE_BASE_URL + "/" + athleteId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway AthleteServiceClient deleteAthlete exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }


        return null;
    }

    public AthleteResponseModel addAthlete(AthleteRequestModel athleteRequestModel, Stats stats) {



        try {
            String url = ATHLETE_SERVICE_BASE_URL;

            AthleteResponseModel athleteResponseModel = restTemplate.postForObject(url, athleteRequestModel, AthleteResponseModel.class, stats);

            return athleteResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway AthleteServiceClient addAthlete exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void updateAthleteById(AthleteRequestModel athleteRequestModel, String athleteId) {

        try {
            String url = ATHLETE_SERVICE_BASE_URL + "/" + athleteId;

            restTemplate.put(url, athleteRequestModel);



        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway AthleteServiceClient updateAthlete exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
        return null;
    }


}
