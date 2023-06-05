package com.sportsmanagement.coachservice.coachmanagementsubdomain.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Integer> {

    Coach findByCoachIdentifier_CoachId(String coachId);
    Boolean existsByCoachIdentifier_CoachId(String coachId);

    List<Coach> findByFirstName(String firstName);

    List<Coach> findByLastName(String lastName);

    List<Coach> findByEmailAddress(String emailAddress);
}
