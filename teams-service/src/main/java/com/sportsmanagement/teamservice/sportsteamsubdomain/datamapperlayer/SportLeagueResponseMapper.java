package com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer;


import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportsLeague;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.SportResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SportLeagueResponseMapper {

    @Mapping(expression = "java(sportsLeague.getSportIdentifier().getSportId())", target = "sportId")
    SportResponseModel entityToResponseModel(SportsLeague sportsLeague);

    List<SportResponseModel> entityListToResponseModelList(List<SportsLeague> sportsLeagues);

}
