package com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CoachResponseModel {

    private final String coachId;
    private final String teamId;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String phoneNumber;
    private final Double salary;
    private final String title;
    //private final String title;
    //private final Integer yearsInPosition;

}
