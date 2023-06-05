USE `athlete-db`;

create table if not exists athletes (
                                       id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       athlete_id VARCHAR(36),
    team_id VARCHAR(10),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email_address VARCHAR(50),
    sport_name VARCHAR(50),
    points_won DECIMAL(8, 2),
    games_won DECIMAL(8, 2),
    games_lost DECIMAL(8, 2),
    height DECIMAL(8, 2),
    weight DECIMAL(8, 2),
    age DECIMAL(8, 2),
    gender VARCHAR(50),
    status VARCHAR(50)
    );

