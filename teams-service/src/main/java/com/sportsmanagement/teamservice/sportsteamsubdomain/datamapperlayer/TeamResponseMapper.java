package com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer;


import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team.Team;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.TeamResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamResponseMapper {

    @Mapping(expression = "java(team.getTeamIdentifier().getTeamId())", target = "teamIdentifier")
    @Mapping(expression = "java(team.getSportIdentifier().getSportId())", target = "sportId")

    TeamResponseModel entityToResponseModel(Team team);

    List<TeamResponseModel> entityListToResponseModelList(List<Team> teams);
}
