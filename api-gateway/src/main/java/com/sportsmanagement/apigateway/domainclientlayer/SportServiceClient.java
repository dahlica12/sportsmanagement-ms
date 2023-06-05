package com.sportsmanagement.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.apigateway.presentationlayer.Athlete.AthleteResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.SportsTeam.*;
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
import java.util.List;

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

            assert sportsTeamResponseModel != null;
            log.debug("5. Received in API-Gateway SportServiceClient getSportsTeamAggregate with SportsTeamResponseModel: "
                    + sportsTeamResponseModel.getSportId());

            return sportsTeamResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient getSportsTeamAggregate exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public List<SportsTeamResponseModel> getAllSportsLeagues() {

        try {
            String url = SPORT_SERVICE_BASE_URL;

            ResponseEntity<List<SportsTeamResponseModel>> responseEntity  = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<SportsTeamResponseModel>>() {
                    });
            List<SportsTeamResponseModel> sports = responseEntity.getBody();
            return sports;
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in api-gateway Sportserviceclient getAllSports exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void deleteSportById(String SportId) {


        try {
            String url = SPORT_SERVICE_BASE_URL + "/" + SportId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient deleteSport exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }


        return null;
    }

    public SportResponseModel addSport(SportRequestModel SportRequestModel) {



        try {
            String url = SPORT_SERVICE_BASE_URL;

            SportResponseModel SportResponseModel = restTemplate.postForObject(url, SportRequestModel, SportResponseModel.class);

            return SportResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient addSport exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public List<TeamResponseModel> getAllTeamsBySportId(String sportId) {


        if (sportId == null){
            throw new NotFoundException("following sport id does not exist " + sportId);
        }

        try {
            String url = SPORT_SERVICE_BASE_URL + "/" + sportId + "/teams";

            List<TeamResponseModel> teams = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<TeamResponseModel>>() {
                    }).getBody();

            return teams;
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in api-gateway Sportserviceclient getAllSports exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public TeamResponseModel addTeamToASportsLeague(TeamRequestModel teamRequestModel, String sportId) {

        try {
            String url = SPORT_SERVICE_BASE_URL;

            TeamResponseModel teamResponseModel = restTemplate.postForObject(url, teamRequestModel, TeamResponseModel.class, sportId);

            return teamResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient addTeamToASportsLeague exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void removeTeamInSportsLeague(String sportId, String teamId) {


        if (sportId == null){
            throw new NotFoundException("following sport id does not exist " + sportId);
        }

        if (teamId == null){
            throw new NotFoundException("following team id does not exist " + teamId);
        }

        try {
            String url = SPORT_SERVICE_BASE_URL + "/" + sportId + "/" + "teams" + teamId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway SportServiceClient removeTeamInSportsLeague exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }


        return null;
    }
}
