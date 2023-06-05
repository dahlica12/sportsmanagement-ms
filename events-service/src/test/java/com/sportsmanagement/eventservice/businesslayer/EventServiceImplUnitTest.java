package com.sportsmanagement.eventservice.businesslayer;

import com.sportsmanagement.eventservice.datalayer.EventRepository;
import com.sportsmanagement.eventservice.presentationlayer.EventResponseModel;
import com.sportsmanagement.eventservice.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EventServiceImplUnitTest {


    @Autowired
    EventService eventService;

    @MockBean
    EventRepository eventRepository;


//    @Test
//    void whenEventIsValid_ShouldReturnEventId() {
//
//        //arrange
//        String athleteEventId = "d3b7ddaa-b890-4114-89ac-4838d02ec13e";
//        EventResponseModel event = new EventResponseModel("")
//
//
//        when(eventService.getAthleteEventByAthleteId(athleteEventId))
//                .thenReturn(event);
//
//        //act
//        EventResponseModel realEvent = eventService.getAthleteEventByAthleteId(athleteEventId);
//
//
//        //assert
//        assertNotNull(realEvent);
//        assertEquals(event, realEvent);

//    }
//    @Test
//    void whenInvalidEventId_OnGetEventById_thenThrow_NotFoundException() {
//
//        String invalidEventId = "321111";
//
//        when(eventService.getAthleteEventByAthleteId(invalidEventId))
//                .thenReturn(null);
//
//        assertThrows(NotFoundException.class, () -> {
//            eventService.getAthleteEventByAthleteId(invalidEventId);
//        });
//    }
}
