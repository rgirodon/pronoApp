INSERT INTO UTILISATEUR (login, password, admin, points) 
	   VALUES ('rgirodon@sqli.com', 'password', true, 1);
	   
INSERT INTO UTILISATEUR (login, password, admin, points) 
	   VALUES ('rgirodon2000@yahoo.fr','password', false, 0);	   

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED )
VALUES ( 'France', 'Ukraine', 3, 0, '2013-11-19', true);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED )
VALUES ( 'Suède', 'Portugal', 2, 3, '2013-11-19', true);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED )
VALUES ( 'France', 'Pays-Bas', NULL, NULL, '2014-03-05', false);