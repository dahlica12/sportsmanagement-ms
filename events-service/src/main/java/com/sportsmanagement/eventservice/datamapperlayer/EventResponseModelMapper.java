package com.sportsmanagement.eventservice.datamapperlayer;

import com.sportsmanagement.eventservice.datalayer.Event;
import com.sportsmanagement.eventservice.presentationlayer.EventResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventResponseModelMapper {

    @Mapping(expression = "java(event.getEventIdentifier().getEventId())", target = "eventId")
    @Mapping(expression = "java(event.getSportIdentifier().getSportId())", target = "sportId")
    @Mapping(expression = "java(event.getTeamIdentifier().getTeamId())", target = "teamId")
    @Mapping(expression = "java(event.getAthleteIdentifier().getAthleteId())", target = "athleteId")
    @Mapping(expression = "java(event.getCoachIdentifier().getCoachId())", target = "coachId")
    EventResponseModel entityToResponseModel(Event event);

    List<EventResponseModel> entityListToResponseModelList(List<Event> events);
}
