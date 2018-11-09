-- INSERTING EXAMPLE USERS --

INSERT INTO users VALUES 
    ("JSmith24", "0123456789qwertyuiop0123456789", 1);

INSERT INTO student VALUES 
    ("JSmith24", "Mr", "John", "Smith", "Professor Oak", "jsmith24@shef.ac.uk");

INSERT INTO users VALUES
    ("PGreen", "qwertyuiopasdfghjkl;1234567890", 4),
    ("HWhite", "123456789012345678901234567890", 3),
    ("MBlack", "123456789012345678901234567890", 2);

-- INSERTING EXAMPLE DEGREES AND DEPARTMENTS --

INSERT INTO degree VALUES 
    ("COMU03", "MEng Software Engineering");

INSERT INTO degree_level VALUES 
    ("COMU03", 1),
    ("COMU03", 2),
    ("COMU03", 3),
    ("COMU03", 4);

INSERT INTO degree VALUES 
    ("COMU49", "BSc Philosophy and Computer Science");

INSERT INTO degree_level VALUES
    ("COMU49", 1),
    ("COMU49", 2),
    ("COMU49", 3);

INSERT INTO department VALUES 
    ("COM", "Computer Science"),
    ("PHI", "Philosophy"),
    ("RSS", "Russian and Slavonic Studies");

INSERT INTO associated_departments VALUES
    ("COMU49", "COM", 1),
    ("COMU49", "PHI", 0),
    ("COMU03", "COM", 1);

-- INSERT INTO MODULE AND GRADES TABLE --

INSERT INTO module VALUES 
    ("COM1005", "Machines and Intelligence", 20),
    ("COM1001", "Software Engineering", 15); 

INSERT INTO core VALUES
    ("COM1005", "COMU49", 0),
    ("COM1005", "COMU03", 1),
    ("COM1001", "COMU49", 0);

INSERT INTO grades VALUES 
    ("JSmith24", "COM1005", "74.55655", null),
    ("JSmith24", "COM1001", "20.001", "50.5555");
