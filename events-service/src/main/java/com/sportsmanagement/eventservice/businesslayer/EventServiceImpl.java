package com.sportsmanagement.eventservice.businesslayer;

import com.sportsmanagement.eventservice.datalayer.*;
import com.sportsmanagement.eventservice.datamapperlayer.EventResponseModelMapper;
import com.sportsmanagement.eventservice.domainclientlayer.*;
import com.sportsmanagement.eventservice.presentationlayer.EventRequestModel;
import com.sportsmanagement.eventservice.presentationlayer.EventResponseModel;
import com.sportsmanagement.eventservice.utils.exceptions.InvalidInputException;
import com.sportsmanagement.eventservice.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {


    private final EventRepository eventRepository;
    private final EventResponseModelMapper eventResponseModelMapper;
    private final AthleteServiceClient athleteServiceClient;
    private final CoachServiceClient coachServiceClient;
    private final SportServiceClient sportServiceClient;


    @Override
    public List<EventResponseModel> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventResponseModelMapper.entityListToResponseModelList(events);
    }



    @Override
    public EventResponseModel processSportEvent(EventRequestModel eventRequestModel, String sportId) {



        AthleteResponseModel athleteResponseModel = athleteServiceClient.getAthleteByAthleteId(eventRequestModel.getAthleteId());

        if(athleteResponseModel == null) {
            throw new NotFoundException("Unknown athlete provided " + eventRequestModel.getAthleteId());
        }


        CoachResponseModel coachResponseModel = coachServiceClient.getCoachByCoachId(eventRequestModel.getCoachId());

        if (coachResponseModel == null) {
            throw new NotFoundException("Unknown coachId provided: " + eventRequestModel.getCoachId());
        }

        SportsTeamResponseModel sportsTeamResponseModel = sportServiceClient.getSportAggregate(sportId);

        if (sportsTeamResponseModel == null){
            throw new NotFoundException("Unknown sportId provided: " + sportId);
        }

        TeamResponseModel teamResponseModel = sportServiceClient.getTeamByTeamId(sportId, eventRequestModel.getTeamId());

        if(teamResponseModel == null) {
            throw new NotFoundException("Team with teamId: " + eventRequestModel.getTeamId() +
                    " is not in sports league with sportId " + sportId);
        }


        Address address = Address.builder()
                .streetAddress(eventRequestModel.getStreetAddress())
                .city(eventRequestModel.getCity())
                .province(eventRequestModel.getProvince())
                .country(eventRequestModel.getCountry())
                .postalCode(eventRequestModel.getPostalCode())
                .build();


        //build the full event document
        Event event = Event.builder()
                .eventIdentifier(new EventIdentifier())
                .sportIdentifier(new SportIdentifier(sportsTeamResponseModel.getSportId()))
                .teamIdentifier(new TeamIdentifier(teamResponseModel.getTeamIdentifier()))
                .athleteIdentifier(new AthleteIdentifier(athleteResponseModel.getAthleteId()))
                .coachIdentifier(new CoachIdentifier(coachResponseModel.getCoachId()))
                .team1Name(teamResponseModel.getTeamName())
                .team2Name(teamResponseModel.getTeamName())
                .coach1LastName(coachResponseModel.getLastName())
                .coach2LastName(coachResponseModel.getLastName())
                .eventType(eventRequestModel.getEventType())
                .status(eventRequestModel.getStatus())
                .eventDate(eventRequestModel.getEventDate())
                .eventAddress(address)
                .duration(eventRequestModel.getDuration())
                .score(eventRequestModel.getScore())
                .build();


        Event saved = eventRepository.save(event);

        if (saved == null) {
            throw new InvalidInputException("An error has occurred while saving");
        }

//        //convert teamResponseModel into TeamRequestModel
//        TeamRequestModel teamRequestModel = TeamRequestModel.builder()
//                .teamId(teamResponseModel.getTeamIdentifier())
//                .sportId(teamResponseModel.getSportId())
//                .teamName(teamResponseModel.getTeamName())
//                .level(teamResponseModel.getLevel())
//                .sportType(teamResponseModel.getSportType())
//                .build();

        //convert sportTeamResponseModel into SportRequestModel
        SportRequestModel sportRequestModel = SportRequestModel.builder()
                .name(sportsTeamResponseModel.getName())
                .build();

        return eventResponseModelMapper.entityToResponseModel(event);
    }



//    @Override
//    public EventResponseModel getEventById(String eventId) {
//
//    }


}
