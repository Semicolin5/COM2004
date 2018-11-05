
--- INSERTING EXAMPLE USERS ---

INSERT INTO user VALUES 
    ("JSmith24", "0123456789qwertyuiop0123456789", 1);

INSERT INTO student VALUES 
    ("JSmith24", "Mr", "John", "Smith", "Professor Oak", "jsmith24@shef.ac.uk");

INSERT INTO user VALUES
    ("PGreen", "qwertyuiopasdfghjkl;1234567890", 4),
    ("HWhite", "123456789012345678901234567890", 3),
    ("MBlack", "123456789012345678901234567890", 2);



--- INSERTING EXAMPLE DEGREES AND DEPARTMENTS ---

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
    ("COMu49", 3);

INSERT INTO department VALUES 
    ("COM", "Computer Science"),
    ("PHI", "Philosophy");

INSERT INTO associated_departments VALUES
    ("COMU49", "COM", 1),
    ("COMU49", "PHI", 0),
    ("COMU03", "COM", 1)

 



