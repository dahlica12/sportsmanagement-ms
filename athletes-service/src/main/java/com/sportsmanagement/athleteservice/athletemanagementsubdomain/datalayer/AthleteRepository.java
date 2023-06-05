package com.sportsmanagement.athleteservice.athletemanagementsubdomain.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {

    Athlete findByAthleteIdentifier_AthleteId(String athleteId);
    Boolean existsByAthleteIdentifier_AthleteId(String athleteId);

    List<Athlete> findByEmailAddress(String invalidEmailAddress);

    List<Athlete> findByFirstName(String invalidFirstName);

    List<Athlete> findByLastName(String invalidLastName);
}
