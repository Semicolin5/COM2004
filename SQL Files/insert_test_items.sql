-- INSERTING EXAMPLE USERS --

INSERT INTO users VALUES
    (1004, "4FA4FB13F6C0C3A6675AE154FCD37453", "D0BD9FBBEC9F0115EEB312F762F585DAE9ED97DBA7E72505C1298C9EB82A1E2E", 1);

INSERT INTO degree VALUES
    ("COMU49", "BSc Philosophy and Computer Science", 0, 0);

INSERT INTO student VALUES
    (1004, "Mr", "John", "Smith", "Professor Oak", "JSmith1@sheffieldringroad.ac.uk", "COMU49");

INSERT INTO users VALUES
    (800765, "qwertyuiopasdfghjkl;1234567890", "JIHFDSKJFMSHSK145", 4),
    (689879, "123456789012345678901234567890", "JIHFDSKJ54RESK145", 3),
    (972097, "123456789012345678901234567890", "JIHFDSKJFMSHSK145", 2);

-- INSERTING EXAMPLE DEGREES AND DEPARTMENTS --

INSERT INTO degree VALUES
    ("COMU03", "MEng Software Engineering", 1, 0);

INSERT INTO department VALUES
    ("COM", "Computer Science"),
    ("PHI", "Philosophy"),
    ("RSS", "Russian and Slavonic Studies");

INSERT INTO degree_department VALUES
    ("COMU49", "COM", 1),
    ("COMU49", "PHI", 0),
    ("COMU03", "COM", 1);

-- INSERT INTO MODULE AND GRADES TABLE --

INSERT INTO module VALUES
    ("COM1005", "Machines and Intelligence", 20, 1),
    ("COM1001", "Software Engineering", 15, 2),
    ("RSS2109", "The Russian Language and Russian Society through Dance", 20, 3);

INSERT INTO module_degree VALUES
    ("COM1005", "COMU49", 1, 0),
    ("COM1005", "COMU03", 1, 1),
    ("COM1001", "COMU49", 1, 0),
    ("RSS2109", "COMU49", 2, 0);

INSERT INTO grades VALUES
    (1004, "COM1005", "74.55655", null),
    (1004, "COM1001", "20.001", "50.5555");
