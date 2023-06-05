USE `coach-db`;

create table if not exists coaches (
                                         id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         coach_id VARCHAR(36),
    team_id VARCHAR (50),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email_address VARCHAR(50),
    phone_number VARCHAR(50),
    salary DECIMAL(8, 2),
    title VARCHAR (50)
    );



