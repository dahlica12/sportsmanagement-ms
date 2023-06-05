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
public class SportServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String SPORT_SERVICE_BASE_URL;


    public SportServiceClient(RestTemplate restTemplate,
                                  ObjectMapper mapper,
                                  @Value("${app.teams-service.host}") String teamsServiceHost,
                                  @Value("${app.teams-service.port}") String teamsServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        SPORT_SERVICE_BASE_URL = "http://" + teamsServiceHost + ":" + teamsServicePort +
                "/api/v1/sports";
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();
        }

    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
//include all possible responses from the client
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

    public SportsTeamResponseModel getSportAggregate(String sportId) {
        log.debug("3. Received in API-Gateway SportServiceClient getSportsTeamAggregate with sportId: " + sportId);

        try {
            String url = SPORT_SERVICE_BASE_URL + "/" + sportId;

            SportsTeamResponseModel sportsTeamResponseModel = restTemplate.getForObject(url, SportsTeamResponseModel.class);

            log.debug("5. Received in Event-Service SportServiceClient getSportsTeamAggregate with SportsTeamResponseModel: "
                    + sportsTeamResponseModel.getSportId());

            return sportsTeamResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient getSportsTeamAggregate exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public TeamResponseModel getTeamByTeamId(String sportId, String teamId) {
        try {
            String url = SPORT_SERVICE_BASE_URL + "/" + sportId + "/" + "teams" + "/" + teamId;
            log.debug("Requesting URL:", url);

            TeamResponseModel teamResponseModel = restTemplate.getForObject(url, TeamResponseModel.class);

            return teamResponseModel;


        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in Aggregator SportServiceClient getTeamByTeamId exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }


    }
}
