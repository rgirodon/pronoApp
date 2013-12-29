INSERT INTO LEAGUE (name, isPublic) 
	   VALUES ('Brasil 2014', true);

INSERT INTO UTILISATEUR (login, password, admin, displayName) 
	   VALUES ('rgirodon@sqli.com', 'password', true, 'rgirodon_pro');
	   
INSERT INTO UTILISATEUR (login, password, admin, displayName) 
	   VALUES ('rgirodon2000@yahoo.fr','password', false, 'rgirodon_perso');
	   
INSERT INTO LEAGUE_PARTICIPATION
( UTILISATEUR_ID, LEAGUE_ID, ADMIN, POINTS, NB_CORRECT_RESULTS, NB_EXACT_SCORES, NB_COMPUTED_PRONOS )
VALUES ( 1, 1, true, 0, 0, 0, 0);	

INSERT INTO LEAGUE_PARTICIPATION
( UTILISATEUR_ID, LEAGUE_ID, ADMIN, POINTS, NB_CORRECT_RESULTS, NB_EXACT_SCORES, NB_COMPUTED_PRONOS )
VALUES ( 2, 1, false, 0, 0, 0, 0);	

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED, LEAGUE_ID )
VALUES ( 'France', 'Ukraine', 3, 0, '2013-11-19', true, true, 1);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED, LEAGUE_ID )
VALUES ( 'Su�de', 'Portugal', 2, 3, '2013-11-19', true, false, 1);

INSERT INTO GAME
( TEAM1, TEAM2, SCORETEAM1, SCORETEAM2, DATE, CLOSED, POINTSCOMPUTED, LEAGUE_ID )
VALUES ( 'France', 'Pays-Bas', NULL, NULL, '2014-03-05', false, false, 1);