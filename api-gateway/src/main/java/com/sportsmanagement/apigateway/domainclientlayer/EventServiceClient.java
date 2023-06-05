package com.sportsmanagement.apigateway.domainclientlayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportsmanagement.apigateway.presentationlayer.Coach.CoachResponseModel;
import com.sportsmanagement.apigateway.presentationlayer.Event.EventRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Event.EventResponseModel;
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
public class EventServiceClient {


    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;

    private final String EVENT_SERVICE_BASE_URL;


    public EventServiceClient(RestTemplate restTemplate,
                              ObjectMapper mapper,
                              @Value("${app.events-service.host}") String eventsServiceHost,
                              @Value("${app.events-service.port}") String eventsServicePort) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        EVENT_SERVICE_BASE_URL = "http://" + eventsServiceHost + ":" + eventsServicePort +
                "/api/v1/";

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

        return ex;
    }

    public List<EventResponseModel> getAllEvents() {

        try {
            String url = EVENT_SERVICE_BASE_URL + "/events";

            ResponseEntity<List<EventResponseModel>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<EventResponseModel>>() {
                    });
            List<EventResponseModel> events = responseEntity.getBody();
            return events;
        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in api-gateway coacheserviceclient getAllcoaches exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }

    public EventResponseModel processSportEvent(EventRequestModel eventRequestModel, String sportId){
        try {
            String url = EVENT_SERVICE_BASE_URL + "sports/" + sportId + "/events";

            EventResponseModel eventResponseModel = restTemplate.postForObject(url, eventRequestModel, EventResponseModel.class);

            return eventResponseModel;

        } catch (HttpClientErrorException ex) {
            log.debug("5. Received in API-Gateway EventServiceClient addEvent exception: "
                    + ex.getMessage());

            throw handleHttpClientException(ex);
        }
    }
}
