INSERT INTO UTILISATEUR (login, password, admin, points) 
	   VALUES ('rgirodon@sqli.com', 'password', true, 1);
	   
INSERT INTO UTILISATEUR (login, password, admin, points) 
	   VALUES ('rgirodon2000@yahoo.fr','password', false, 0);	   

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED )
VALUES ( 'France', 'Ukraine', 3, 0, '2013-11-19', true, true);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED )
VALUES ( 'Su�de', 'Portugal', 2, 3, '2013-11-19', true, false);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED )
VALUES ( 'France', 'Pays-Bas', NULL, NULL, '2014-03-05', false, false);