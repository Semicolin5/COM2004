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
    personal_tutor VARCHAR (50), 
    email VARCHAR (50),
    PRIMARY KEY (login),
    FOREIGN KEY (login) REFERENCES user (login)
);

CREATE TABLE department (
    code CHAR (3),
    name VARCHAR (100),
    PRIMARY KEY (code)
);

CREATE TABLE degree (
    code CHAR (6),
    name VARCHAR (100),
    PRIMARY KEY (code)
);

CREATE TABLE degree_level (
    code CHAR (6),
    level INT (1),
    PRIMARY KEY (code, level),
    FOREIGN KEY (code) REFERENCES degree (code)
);

-- linker table to store degree associations with departments, 
-- and whether a department is the lead department
CREATE TABLE associated_departments (
    degree_code CHAR (6),
    department_code CHAR (3),
    lead BOOL,
    PRIMARY KEY (degree_code, department_code),
    FOREIGN KEY (degree_code) REFERENCES degree (code),
    FOREIGN KEY (department_code) REFERENCES department (code)
);

