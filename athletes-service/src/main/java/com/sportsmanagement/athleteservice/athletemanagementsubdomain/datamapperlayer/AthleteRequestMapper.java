package com.sportsmanagement.athleteservice.athletemanagementsubdomain.datamapperlayer;

import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Athlete;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.AthleteIdentifier;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer.Stats;
import com.sportsmanagement.athleteservice.athletemanagementsubdomain.presentationlayer.AthleteRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AthleteRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(athleteIdentifier)", target = "athleteIdentifier", ignore = true),
            @Mapping(expression = "java(stats)", target = "stats"),

    })
    Athlete requestModelToEntity(AthleteRequestModel athleteRequestModel,  Stats stats);
}
