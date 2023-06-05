package com.sportsmanagement.coachservice.coachmanagementsubdomain.datamapperlayer;

import com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer.Coach;
import com.sportsmanagement.coachservice.coachmanagementsubdomain.presentationlayer.CoachRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CoachRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(coachIdentifier)", target = "coachIdentifier", ignore = true),
    })

    Coach requestModelToEntity(CoachRequestModel coachRequestModel);
}
