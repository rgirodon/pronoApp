DELETE FROM LEAGUE_PARTICIPATION;
DELETE FROM PRONOSTIC;
DELETE FROM UTILISATEUR;
DELETE FROM GAME;
DELETE FROM INVITATION;
DELETE FROM LEAGUE;

INSERT INTO LEAGUE (id, name, isPublic) 
	   VALUES (1, 'LIGUE 1', true);
	   
psql \copy GAME FROM 'D:\projets\pronoApp\src\main\sql\postgresql\prod\game.csv' WITH DELIMITER AS ',' CSV HEADER;

psql \copy UTILISATEUR FROM 'D:\projets\pronoApp\src\main\sql\postgresql\prod\utilisateur.csv' WITH DELIMITER AS ',' CSV HEADER;		  

psql \copy LEAGUE_PARTICIPATION FROM 'D:\projets\pronoApp\src\main\sql\postgresql\prod\league_participation.csv' WITH DELIMITER AS ',' CSV HEADER;
		  
psql \copy PRONOSTIC FROM 'D:\projets\pronoApp\src\main\sql\postgresql\prod\pronostic.csv' WITH DELIMITER AS ',' CSV HEADER;		

ALTER SEQUENCE game_id_seq RESTART WITH 1000;
ALTER SEQUENCE invitation_id_seq RESTART WITH 1000;  
ALTER SEQUENCE league_id_seq RESTART WITH 1000;
ALTER SEQUENCE utilisateur_id_seq RESTART WITH 1000;
ALTER SEQUENCE pronostic_id_seq RESTART WITH 1000;
ALTER SEQUENCE league_participation_id_seq RESTART WITH 1000;

UPDATE UTILISATEUR SET PASSWORD = md5(PASSWORD);

ALTER TABLE invitation DROP CONSTRAINT invitation_email_key;