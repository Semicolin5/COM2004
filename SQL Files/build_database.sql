CREATE TABLE users (
    login_id int,
    password CHAR (64), -- stores hash code
    privilege INT NOT NULL,
    salt CHAR (32),
    PRIMARY KEY (login_id)
);

CREATE TABLE degree (
    code CHAR (6),
    name VARCHAR (100),
    masters BOOLEAN,
    PRIMARY KEY (code)
);

CREATE TABLE student (
    login_id int,
    title CHAR (2),
    forename VARCHAR (50),
    surname VARCHAR (50),
    personal_tutor VARCHAR (50), 
    degree_code CHAR (6), -- the degree taken
    PRIMARY KEY (login_id),
    FOREIGN KEY (login_id) REFERENCES users (login_id),
    FOREIGN KEY (degree_code) REFERENCES degree (code)
);

CREATE TABLE period_of_study (
    label CHAR (1),
    login_id int,
    level_of_study CHAR (1), -- the level of study
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (label, login_id),
    FOREIGN KEY (login_id) REFERENCES student (login_id)
);

CREATE TABLE department (
    code CHAR (3),
    name VARCHAR (100),
    PRIMARY KEY (code)
);

-- linker table to store how the departments associated with each degrees, 
-- and whether a department is the lead department
CREATE TABLE associated_department (
    degree_code CHAR (6),
    department_code CHAR (3),
    lead_department BOOLEAN,
    PRIMARY KEY (degree_code, department_code),
    FOREIGN KEY (degree_code) REFERENCES degree (code),
    FOREIGN KEY (department_code) REFERENCES department (code)
);

CREATE TABLE module (
    code CHAR (7),
    name VARCHAR (100),
    credits VARCHAR (3), 
    PRIMARY KEY (code)
);

-- linker table to store how the module is associated with each degree,
-- whether it is a core module for each degree that allows the module to be taken
CREATE TABLE core (
    module_code CHAR (7),
    degree_code CHAR (6),
    degree_level CHAR(1),
    core BOOLEAN,
    PRIMARY KEY (module_code, degree_code),
    FOREIGN KEY (module_code) REFERENCES module (code),
    FOREIGN KEY (degree_code) REFERENCES degree (code)    
);

-- linker table to represent the modules a student is taking, and to store
-- their grades for the module. 
CREATE TABLE grades (
    student_id int,
    module_code CHAR (7),
    initial_percent DECIMAL(8, 5), -- marks achieved
    resit_percent DECIMAL(8, 5), 
    PRIMARY KEY (student_id, module_code),
    FOREIGN KEY (student_id) REFERENCES student (login_id),
    FOREIGN KEY (module_code) REFERENCES module (code)
);

