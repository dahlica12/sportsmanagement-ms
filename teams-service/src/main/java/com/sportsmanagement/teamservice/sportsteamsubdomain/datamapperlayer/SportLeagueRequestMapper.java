package com.sportsmanagement.teamservice.sportsteamsubdomain.datamapperlayer;


import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportsLeague;
import com.sportsmanagement.teamservice.sportsteamsubdomain.presentationlayer.SportRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SportLeagueRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sportIdentifier", ignore = true)
    SportsLeague requestModelToEntity(SportRequestModel requestModel);
}
