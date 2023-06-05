package com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.team;

import com.sportsmanagement.teamservice.sportsteamsubdomain.datalayer.sport.SportIdentifier;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "teams")
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "team_id")
    @Embedded
    private TeamIdentifier teamIdentifier;

    @Embedded
    @Column(name = "sport_id")
    private SportIdentifier sportIdentifier;


    @Column(name = "team_name")
    private String teamName;


    @Column(name = "sport_type")
    private String sportType;


    @Column(name = "team_level")
    @Enumerated(EnumType.STRING)
    private Level level;


    public Team(){

    }

    public Team(TeamIdentifier teamIdentifier, Level level) {
        this.teamIdentifier = teamIdentifier;
        this.level = level;
    }

    public Team(TeamIdentifier teamIdentifier, SportIdentifier sportIdentifier, String teamName, String sportType, Level level) {
        this.teamIdentifier = teamIdentifier;
        this.sportIdentifier = sportIdentifier;
        this.teamName = teamName;
        this.sportType = sportType;
        this.level = level;
    }
}
