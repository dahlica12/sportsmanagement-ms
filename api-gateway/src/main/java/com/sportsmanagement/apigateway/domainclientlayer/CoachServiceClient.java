package com.sportsmanagement.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachRequestModel;
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
public class CoachServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String COACH_SERVICE_BASE_URL;


    public CoachServiceClient(RestTemplate restTemplate,
                                ObjectMapper mapper,
                                @Value("${app.coaches-service.host}") String coachesServiceHost,
                                @Value("${app.coaches-service.port}") String coachesServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        COACH_SERVICE_BASE_URL = "http://" + coachesServiceHost + ":" + coachesServicePort +
                "/api/v1/coaches";
        log.debug(COACH_SERVICE_BASE_URL);
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
            return ioex.getMessage();
        }

    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
//include all possible responses from the coaches
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

    public CoachResponseModel getCoachById(String coachId) {
        log.debug("3. Received in API-Gateway CoachServiceClient getCoachById with coachId: " + coachId);

        try {
            String url = COACH_SERVICE_BASE_URL + "/" + coachId;

            CoachResponseModel coachResponseModel = restTemplate.getForObject(url, CoachResponseModel.class);

            log.debug("5. Received in API-Gateway CoachServiceClient getCoachById with CoachResponseModel: "
                    + coachResponseModel.getCoachId());

            return coachResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway CoachServiceClient getCoachById exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public List<CoachResponseModel> getAllCoaches() {

        try {
            String url = COACH_SERVICE_BASE_URL;

            ResponseEntity<List<CoachResponseModel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<CoachResponseModel>>() {
                    });
            List<CoachResponseModel> coaches = responseEntity.getBody();
            return coaches;
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in api-gateway coacheserviceclient getAllcoaches exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void deleteCoachById(String coachId) {

        try {
            String url = COACH_SERVICE_BASE_URL + "/" + coachId;

            restTemplate.delete(url);
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway CoachServiceClient deleteCoach exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }


        return null;
    }

    public CoachResponseModel addCoach(CoachRequestModel CoachRequestModel) {



        try {
            String url = COACH_SERVICE_BASE_URL;

            CoachResponseModel CoachResponseModel = restTemplate.postForObject(url, CoachRequestModel, CoachResponseModel.class);

            return CoachResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway CoachServiceClient addCoach exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public Void updateCoachById(CoachRequestModel CoachRequestModel, String CoachId) {

        try {
            String url = COACH_SERVICE_BASE_URL + "/" + CoachId;

            restTemplate.put(url, CoachResponseModel.class);



        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway CoachServiceClient updateCoach exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
        return null;
    }
}
