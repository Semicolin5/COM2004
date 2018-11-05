CREATE TABLE user (
    login VARCHAR (30),
    password CHAR (40), -- stores hash code
    priviledge INT NOT NULL,
    PRIMARY KEY (login)
);

CREATE TABLE student (
    login VARCHAR (30), 
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

-- linker table to store how the departments associated with each degrees, 
-- and whether a department is the lead department
CREATE TABLE associated_departments (
    degree_code CHAR (6),
    department_code CHAR (3),
    lead BOOLEAN,
    PRIMARY KEY (degree_code, department_code),
    FOREIGN KEY (degree_code) REFERENCES degree (code),
    FOREIGN KEY (department_code) REFERENCES department (code)
);

CREATE TABLE module (
    code CHAR (7),
    name VARCHAR (100),
    credits VARCHAR (3), -- Assuming that credits are same regardless of level (if not then we move this field to linker table)
    PRIMARY KEY (code)
);

-- linker table to store how the module is associated with each degree,
-- whether it is a core module for each degree that allows the module to be taken
CREATE TABLE core (
    module_code CHAR (7),
    degree_code CHAR (6),
    core BOOLEAN,
    PRIMARY KEY (module_code, degree_code),
    FOREIGN KEY (module_code) REFERENCES module (code),
    FOREIGN KEY (degree_code) REFERENCES degree (code)    
);

CREATE TABLE grades (
    student_id VARCHAR (30),
    module_code CHAR (7),
    initial_percent DECIMAL(8, 5), -- marks achieved
    resit_percent DECIMAL(8, 5), 
    PRIMARY KEY (student_id, module_code),
    FOREIGN KEY (student_id) REFERENCES student (login)
);





















