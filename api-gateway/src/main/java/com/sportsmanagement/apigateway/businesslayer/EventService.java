package com.sportsmanagement.apigateway.businesslayer;

import com.sportsmanagement.apigateway.presentationlayer.Event.EventRequestModel;
import com.sportsmanagement.apigateway.presentationlayer.Event.EventResponseModel;


import java.util.List;

public interface EventService {


    List<EventResponseModel> getAllEvents();
    EventResponseModel processSportEvent(EventRequestModel eventRequestModel, String sportId);
}
