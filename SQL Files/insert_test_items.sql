-- INSERTING EXAMPLE USERS --

INSERT INTO users VALUES
    (1001, "4FA4FB13F6C0C3A6675AE154FCD37453", "D0BD9FBBEC9F0115EEB312F762F585DAE9ED97DBA7E72505C1298C9EB82A1E2E", 1),
    (1002, "F9BB226792654EA3EA8BBA85D9C9BC45", "46C28118BF466B99B2614ECA8653FC344A17C1748B058CE0C3E708A6F7F5E1F2", 2),
    (1003, "08C0773678CB80F7E17832CE3CA7378A", "0B7022F4D4D52911C3A0E4421D56603560ADCA925D36D99931041A12848FD872", 3),
    (1004, "B5B66856E5BE1FA46437AD429094309A", "49080FA8A51EB4D122BB76452FD537BEA357AFC5A5DC88DE14BB05532A4C8B07", 4),
    (2010, "CD92C0598C706830F06B473E3F314588", "60D53940DF87AA311206D66C1F2A09AC632DE8B9BE960DCF75EC333E5493CFA1", 1),
    (2001, "673DEEF3E424521FE6C469A8CEEA4006", "BE2C7AB4B0C4CF66FA272BE738B46E8E0BB1FA8556AB42D48AB1A13DB771F61D", 1);

INSERT INTO degree VALUES
    ("COMU03", "MEng Software Engineering", 1, 0),
    ("PHYP01", "Postgraduate Physical and Spiritual orgone studies", 1, 0),
    ("COMU49", "BSc Philosophy and Computer Science", 0, 0),
    ("DANP72", "MSC Boogie Ballet", 1, 1),
    ("COMU20", "BSc Computer Science & Maths", 0, 0);

INSERT INTO student VALUES
    (1001, "Mr", "John", "Smith", "Professor Oak", "jsmith1@sheffieldringroad.ac.uk", "COMU49"),
    (2001, "Mr", "Bass", "Hunter", "Dr Dre", "bhunter1@sheffieldringroad.ac.uk", "COMU20"),
    (2010, "Ms", "Emmy", "Nem", "Slim Shady", "enem1:sheffieldringroad.ac.uk", "DANP72");

INSERT INTO users VALUES
    (800765, "qwertyuiopasdfghjkl;1234567890", "JIHFDSKJFMSHSK145", 4),
    (689879, "123456789012345678901234567890", "JIHFDSKJ54RESK145", 3),
    (972097, "123456789012345678901234567890", "JIHFDSKJFMSHSK145", 2);

-- INSERTING EXAMPLE DEGREES AND DEPARTMENTS --


INSERT INTO department VALUES
    ("COM", "Computer Science"),
    ("MAS", "Maths"),
    ("PHI", "Philosophy"),
    ("DAN", "Dance"),
    ("RSS", "Russian and Slavonic Studies");

INSERT INTO degree_department VALUES
    ("COMU49", "COM", 1),
    ("COMU49", "PHI", 0),
    ("COMU03", "COM", 1),
    ("COMU20", "COM", 1),
    ("DANP72", "DAN", 1),
    ("COMU20", "MAS", 0);

-- INSERT INTO MODULE AND GRADES TABLE --

INSERT INTO module VALUES
    ("COM1005", "Machines and Intelligence", 20, 1),
    ("COM1001", "Software Engineering", 15, 2),
    ("RSS2109", "The Russian Language and Russian Society through Dance", 20, 3),
    ("COM1003", "Learn to Program in Java", 20, 1),
    ("COM1070", "Ruby Ruby Ruby Ruby Ruby", 20, 3),
    ("COM1008", "Intro to Web Programming", 20, 2),
    ("COM2110", "Functional Programming", 20, 4),
    ("MAS1014", "Introduction to Probability & Statistics", 40, 3),
    ("MAS1008", "Game Theory", 20, 2),
    ("DAN2001", "Dirty Dancing", 10, 1),
    ("MAS1011", "Algebra Intro", 20, 4);

INSERT INTO module_degree VALUES
    ("COM1005", "COMU49", 1, 0),
    ("DAN2001", "DANP72", 1, 1),
    ("COM1005", "COMU03", 1, 1),
    ("COM1001", "COMU49", 1, 0),
    ("RSS2109", "COMU49", 2, 0),
    ("COM1003", "COMU20", 1, 1),
    ("COM1070", "COMU20", 1, 1),
    ("COM1008", "COMU20", 1, 0),
    ("MAS1014", "COMU20", 1, 1),
    ("MAS1008", "COMU20", 1, 0),
    ("MAS1011", "COMU20", 1, 1),
    ("COM2110", "COMU20", 2, 1);

INSERT INTO period_of_study VALUES
    (2001, 'A', '2016-09-03', '2017-07-01', 1, 23.0),
    (2001, 'B', '2017-09-03', '2018-07-01', 1, NULL),
    (1001, 'A', '2014-09-03', '2015-07-01', 1, 12.04),
    (1001, 'B', '2015-09-03', '2016-07-01', 1, 55.49),
    (1001, 'C', '2016-09-03', '2017-07-01', 2, 88.99),
    (1001, 'D', '2017-09-03', '2018-07-01', 3, NULL);

INSERT INTO grades VALUES
    (1001, "COM1005", 'A', "20.5", "21"),
    (1001, "COM1005", 'B', "50.72", NULL),
    (1001, "COM1001", 'A', "20.001", "50.5555"),
    (2001, "COM1008", 'A', 38.05, 88.9409),
    (2001, "RSS2109", 'A', 20.56, 12),
    (2001, "COM1001", 'A', 12.44, 35.000),
    (2001, "COM1070", 'B', 68.99, NULL),
    (2001, "RSS2109", 'B', 72.1, NULL),
    (2001, "COM1003", 'B', 12, 78),
    (2001, "COM1005", 'B', 38.88, 38.88),
    (2001, "MAS1008", 'B', 55, NULL),
    (2001, "MAS1011", 'B', 55, 24);

