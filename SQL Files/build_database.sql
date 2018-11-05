CREATE TABLE user (
    login VARCHAR (30) NOT NULL,
    password CHAR (40), -- stores hash code
    priviledge INT NOT NULL,
    PRIMARY KEY (login)
);

CREATE TABLE student (
    login VARCHAR (30) NOT NULL, 
    title CHAR (2),
    forename VARCHAR (50),
    surname VARCHAR (50),
    email VARCHAR (50),
    PRIMARY KEY (login),
    FOREIGN KEY (login) REFERENCES user (login)
);

