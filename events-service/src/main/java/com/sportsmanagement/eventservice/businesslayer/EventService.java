package com.sportsmanagement.eventservice.businesslayer;

import com.sportsmanagement.eventservice.presentationlayer.EventRequestModel;
import com.sportsmanagement.eventservice.presentationlayer.EventResponseModel;
import com.sportsmanagement.eventservice.presentationlayer.*;

import java.util.List;

public interface EventService {


    List<EventResponseModel> getAllEvents();

    EventResponseModel processSportEvent(EventRequestModel eventRequestModel, String sportId);



}
