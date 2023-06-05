package com.sportsmanagement.eventservice.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.eventservice.utils.HttpErrorInfo;
import com.sportsmanagement.eventservice.utils.exceptions.InvalidInputException;
import com.sportsmanagement.eventservice.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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

    public AthleteResponseModel getAthleteByAthleteId(String athleteId) {
        log.debug("3. Received in API-Gateway AthleteServiceClient getAthleteAggregate with athleteId: " + athleteId);

        try {
            String url = ATHLETE_SERVICE_BASE_URL + "/" + athleteId;

            AthleteResponseModel athleteResponseModel = restTemplate.getForObject(url, AthleteResponseModel.class);

            log.debug("5. Received in API-Gateway AthleteServiceClient getAthleteAggregate with AthleteResponseModel: "
                    + athleteResponseModel.getAthleteId());

            return athleteResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway AthleteServiceClient getAthleteAggregate exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }
}
