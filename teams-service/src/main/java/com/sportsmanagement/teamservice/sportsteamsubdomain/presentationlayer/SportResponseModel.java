package com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SportResponseModel {

    private final String sportId;
    private final String name;
}
