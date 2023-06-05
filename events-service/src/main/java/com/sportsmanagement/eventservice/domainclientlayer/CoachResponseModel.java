package com.sportsmanagement.eventservice.domainclientlayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachResponseModel {

     String coachId;
     String teamId;
     String firstName;
     String lastName;
     String emailAddress;
     String phoneNumber;
     Double salary;
     String title;
    // String title;
    // Integer yearsInPosition;

}
