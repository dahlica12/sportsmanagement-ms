package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.domainclientlayer.AthleteServiceClient;
import com.sportsmanagement.apigateway.domainclientlayer.CoachServiceClient;
import com.sportsmanagement.apigateway.domainclientlayer.EventServiceClient;
import com.sportsmanagement.apigateway.domainclientlayer.SportServiceClient;
import com.sportsmanagement.apigateway.presentationlayer.Event.EventRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Event.EventResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventServiceClient eventServiceClient;


    public EventServiceImpl(EventServiceClient eventServiceClient) {
        this.eventServiceClient = eventServiceClient;
    }

    @Override
    public List<EventResponseModel> getAllEvents() {
         return eventServiceClient.getAllEvents();
    }


    @Override
    public EventResponseModel processSportEvent(EventRequestModel eventRequestModel, String sportId) {
        return eventServiceClient.processSportEvent(eventRequestModel, sportId);
    }



//    @Override
//    public EventResponseModel getEventById(String eventId) {
//
//    }


}
