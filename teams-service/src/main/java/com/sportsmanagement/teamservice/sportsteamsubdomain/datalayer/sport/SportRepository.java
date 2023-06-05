package com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SportRepository extends JpaRepository<SportsLeague, Integer> {

    SportsLeague findBySportIdentifier_SportId(String sportId);
    Boolean existsBySportIdentifier_SportId(String sportId);
}
