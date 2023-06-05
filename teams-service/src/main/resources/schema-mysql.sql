USE `team-db`;

create table if not exists sports (
                                      id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      sport_id VARCHAR(36),
    name VARCHAR(100)
    );


create table if not exists teams (
                                         id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         team_id VARCHAR(50),
                                         sport_id VARCHAR(50),
    team_name VARCHAR(50),
    sport_type VARCHAR(50),
    team_level VARCHAR(50)
    );



