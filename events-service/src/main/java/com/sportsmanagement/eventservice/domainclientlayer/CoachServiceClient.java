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

    public CoachResponseModel getCoachByCoachId(String coachId) {
        log.debug("3. Received in API-Gateway CoachServiceClient getCoachAggregate with coachId: " + coachId);

        try {
            String url = COACH_SERVICE_BASE_URL + "/" + coachId;

            CoachResponseModel coachResponseModel = restTemplate.getForObject(url, CoachResponseModel.class);

            log.debug("5. Received in API-Gateway CoachServiceClient getCoachAggregate with CoachResponseModel: "
                    + coachResponseModel.getCoachId());

            return coachResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway CoachServiceClient getCoachAggregate exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }
}
