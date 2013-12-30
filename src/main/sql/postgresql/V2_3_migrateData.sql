DELETE FROM LEAGUE_PARTICIPATION;
DELETE FROM PRONOSTIC;
DELETE FROM UTILISATEUR;
DELETE FROM GAME;
DELETE FROM INVITATION;
DELETE FROM LEAGUE;

INSERT INTO LEAGUE (id, name, isPublic) 
	   VALUES (1, 'LIGUE 1', true);
	   
psql \copy GAME FROM 'D:\projets\pronoApp\src\main\sql\postgresql\game.csv' WITH DELIMITER AS ',' CSV HEADER;

psql \copy UTILISATEUR FROM 'D:\projets\pronoApp\src\main\sql\postgresql\utilisateur.csv' WITH DELIMITER AS ',' CSV HEADER;		  

psql \copy LEAGUE_PARTICIPATION FROM 'D:\projets\pronoApp\src\main\sql\postgresql\league_participation.csv' WITH DELIMITER AS ',' CSV HEADER;
		  
psql \copy PRONOSTIC FROM 'D:\projets\pronoApp\src\main\sql\postgresql\pronostic.csv' WITH DELIMITER AS ',' CSV HEADER;		  