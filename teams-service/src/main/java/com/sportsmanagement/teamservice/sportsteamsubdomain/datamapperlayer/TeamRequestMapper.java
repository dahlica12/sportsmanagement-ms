package com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer;

import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportIdentifier;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.Team;
import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.TeamIdentifier;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.TeamRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TeamRequestMapper {

   @Mappings({
       @Mapping(target = "id", ignore = true),
       @Mapping(expression = "java(teamIdentifier)", target = "teamIdentifier"),
       @Mapping(expression = "java(sportIdentifier)", target = "sportIdentifier"),

   })
    Team responseModelToEntity(TeamRequestModel teamRequestModel, TeamIdentifier teamIdentifier, SportIdentifier sportIdentifier);
}
