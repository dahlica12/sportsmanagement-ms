package com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findAllBySportIdentifier_SportId(String sportId);
    List<Team> findAllBySportIdentifier_SportIdAndLevel(String sportId, Level level);
    Team findBySportIdentifier_SportIdAndTeamIdentifier_TeamId(String sportId, String teamId);
    Team findByTeamIdentifier_TeamId(String teamId);
}
