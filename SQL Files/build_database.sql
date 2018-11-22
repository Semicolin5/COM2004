#3NF
CREATE TABLE users (
    login_id int,
    saldet CHAR (32),
    hashpass CHAR (64), -- stores hash code
    privilege INT NOT NULL,
    PRIMARY KEY (login_id)
);

#3NF
CREATE TABLE department (
    department_code CHAR (3),
    department_name VARCHAR (100),
    PRIMARY KEY (department_code)
);

#3NF
CREATE TABLE degree (
    degree_code CHAR (6),
    degree_name VARCHAR (100),
    masters BOOLEAN,
    year_industry BOOLEAN,
    PRIMARY KEY (degree_code)
);

#3NF
CREATE TABLE module (
    module_code CHAR (7),
    module_name VARCHAR (100),
    credits VARCHAR (3),
    semester int,
    PRIMARY KEY (module_code)
);

#3NF
CREATE TABLE student (
    login_id int,
    title CHAR (2),
    forename VARCHAR (50),
    surname VARCHAR (50),
    personal_tutor VARCHAR (50),
    email VARCHAR (50),
    degree_code CHAR (6), -- the degree taken
    PRIMARY KEY (login_id),
    FOREIGN KEY (login_id) REFERENCES users (login_id),
    FOREIGN KEY (degree_code) REFERENCES degree (degree_code)
);

#3NF
CREATE TABLE period_of_study (
    login_id int,
    label CHAR (1),
    start_date DATE,
    end_date DATE,
    level_of_study CHAR (1),
    PRIMARY KEY (login_id, label),
    FOREIGN KEY (login_id) REFERENCES student (login_id)
);

-- Linker Tables
-- linker table to store how the departments associated with each degrees, 
-- and whether a department is the lead department

#3NF
CREATE TABLE degree_department (
    degree_code CHAR (6),
    department_code CHAR (3),
    lead_department BOOLEAN,
    PRIMARY KEY (degree_code, department_code),
    FOREIGN KEY (degree_code) REFERENCES degree (degree_code),
    FOREIGN KEY (department_code) REFERENCES department (department_code)
);

-- linker table to store how the module is associated with each degree,
-- whether it is a core module for each degree that allows the module to be taken

#3NF
CREATE TABLE module_degree (
    module_code CHAR (7),
    degree_code CHAR (6),
    degree_level CHAR(1),
    core BOOLEAN,
    PRIMARY KEY (module_code, degree_code),
    FOREIGN KEY (module_code) REFERENCES module (module_code),
    FOREIGN KEY (degree_code) REFERENCES degree (degree_code)    
);

-- linker table to represent the modules a student is taking, and to store
-- their grades for the module.

#3NF 
CREATE TABLE grades (
    student_id int,
    module_code CHAR (7),
    initial_percent DECIMAL(8, 5), -- marks achieved
    resit_percent DECIMAL(8, 5), 
    PRIMARY KEY (student_id, module_code),
    FOREIGN KEY (student_id) REFERENCES student (login_id),
    FOREIGN KEY (module_code) REFERENCES module (module_code)
);

