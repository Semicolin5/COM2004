CREATE TABLE user (
    login VARCHAR (30) NOT NULL,
    password CHAR (100), -- stores hash code
    priviledge INT NOT NULL,
    PRIMARY KEY (login)
);

CREATE TABLE student (
    title VARCHAR (2),
    forename VARCHAR (50),
    email VARCHAR (50),
    login VARCHAR (30) NOT NULL,
    PRIMARY KEY (login),
    FOREIGN KEY (login) REFERENCES user (login)
);

