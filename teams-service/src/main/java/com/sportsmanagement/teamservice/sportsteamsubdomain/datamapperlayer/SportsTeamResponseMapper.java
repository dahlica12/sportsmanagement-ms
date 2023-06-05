package com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer;

import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportsLeague;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.SportsTeamResponseModel;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.TeamResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportsTeamResponseMapper {

    @Mapping(expression = "java(sportsLeague.getSportIdentifier().getSportId())", target = "sportId")
    //@Mapping(expression = "java(teams)", target = "competitiveTeams")
    SportsTeamResponseModel entitiesToResponseModels(SportsLeague sportsLeague, List<TeamResponseModel> teamResponseModels);
}
