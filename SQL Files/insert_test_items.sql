-- INSERTING EXAMPLE USERS --

INSERT INTO users VALUES
    (1004, "0123456789qwertyuiop0123456789", 1, "JIHFDSKJFMSHSK134");

INSERT INTO degree VALUES
    ("COMU49", "BSc Philosophy and Computer Science", 0);

INSERT INTO student VALUES
    (1004, "Mr", "John", "Smith", "Professor Oak", "JSmith1@sheffieldringroad.ac.uk", "COMU49");

INSERT INTO users VALUES
    (800765, "qwertyuiopasdfghjkl;1234567890", 4, "JIHFDSKJFMSHSK145"),
    (689879, "123456789012345678901234567890", 3, "JIHFDSKJ54RESK145"),
    (972097, "123456789012345678901234567890", 2, "JIHFDSKJFMSHSK145");

-- INSERTING EXAMPLE DEGREES AND DEPARTMENTS --

INSERT INTO degree VALUES
    ("COMU03", "MEng Software Engineering", 1);

INSERT INTO department VALUES
    ("COM", "Computer Science"),
    ("PHI", "Philosophy"),
    ("RSS", "Russian and Slavonic Studies");

INSERT INTO associated_department VALUES
    ("COMU49", "COM", 1),
    ("COMU49", "PHI", 0),
    ("COMU03", "COM", 1);

-- INSERT INTO MODULE AND GRADES TABLE --

INSERT INTO module VALUES
    ("COM1005", "Machines and Intelligence", 20),
    ("COM1001", "Software Engineering", 15),
    ("RSS2109", "The Russian Language and Russian Society through Dance", 20);

INSERT INTO core VALUES
    ("COM1005", "COMU49", 1, 0),
    ("COM1005", "COMU03", 1, 1),
    ("COM1001", "COMU49", 1, 0),
    ("RSS2109", "COMU49", 2, 0);

INSERT INTO grades VALUES
    (1004, "COM1005", "74.55655", null),
    (1004, "COM1001", "20.001", "50.5555");
