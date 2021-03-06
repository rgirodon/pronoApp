CREATE TABLE LEAGUE (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
	isPublic BOOLEAN CHECK (isPublic IS NOT NULL),
	name VARCHAR(80) UNIQUE CHECK (name IS NOT NULL),
	inheritsGamesFromLeague_id INTEGER
); 

ALTER TABLE LEAGUE ADD CONSTRAINT LEAGUE_LEAGUE_FK FOREIGN KEY(inheritsGamesFromLeague_id) REFERENCES LEAGUE(ID);

CREATE TABLE INVITATION (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
	league_id INTEGER CHECK (league_id IS NOT NULL),
	email VARCHAR(80) UNIQUE CHECK (email IS NOT NULL)
);

ALTER TABLE INVITATION ADD CONSTRAINT INVITATION_LEAGUE_FK FOREIGN KEY(league_id) REFERENCES LEAGUE(ID);
 
CREATE TABLE UTILISATEUR (
 	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
   	login VARCHAR(80) UNIQUE CHECK (login IS NOT NULL),
   	password VARCHAR(80) CHECK (password IS NOT NULL),
   	admin BOOLEAN CHECK (admin IS NOT NULL),
   	displayName VARCHAR(80) CHECK (displayName IS NOT NULL)
);

CREATE TABLE LEAGUE_PARTICIPATION (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
	utilisateur_id INTEGER CHECK (utilisateur_id IS NOT NULL),
	league_id INTEGER CHECK (league_id IS NOT NULL),
	admin BOOLEAN CHECK (admin IS NOT NULL),
	POINTS INTEGER,
	NB_CORRECT_RESULTS INTEGER,
	NB_EXACT_SCORES INTEGER,
	NB_COMPUTED_PRONOS INTEGER
); 

ALTER TABLE LEAGUE_PARTICIPATION ADD CONSTRAINT LEAGUE_PARTICIPATION_LEAGUE_FK FOREIGN KEY(league_id) REFERENCES LEAGUE(ID);

ALTER TABLE LEAGUE_PARTICIPATION ADD CONSTRAINT LEAGUE_PARTICIPATION_USER_FK FOREIGN KEY(utilisateur_id) REFERENCES UTILISATEUR(ID);
   	
CREATE TABLE GAME (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	team1 VARCHAR(80) CHECK (team1 IS NOT NULL),
	team2 VARCHAR(80) CHECK (team2 IS NOT NULL),
	scoreTeam1 INTEGER,
	scoreTeam2 INTEGER,
	date DATE CHECK (date IS NOT NULL),
	closed BOOLEAN CHECK (closed IS NOT NULL),
	pointsComputed BOOLEAN CHECK (pointsComputed IS NOT NULL),
	league_id INTEGER CHECK (league_id IS NOT NULL)
);

ALTER TABLE GAME ADD CONSTRAINT GAME_LEAGUE_FK FOREIGN KEY(league_id) REFERENCES LEAGUE(ID);

CREATE TABLE PRONOSTIC (
	id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	scoreTeam1 INTEGER CHECK (scoreTeam1 IS NOT NULL),
	scoreTeam2 INTEGER CHECK (scoreTeam2 IS NOT NULL),
	points INTEGER,
	utilisateur_id INTEGER CHECK (utilisateur_id IS NOT NULL),
	game_id INTEGER CHECK (game_id IS NOT NULL)
);

ALTER TABLE PRONOSTIC ADD CONSTRAINT PRONOSTIC_UTILISATEUR_FK FOREIGN KEY(utilisateur_id) REFERENCES UTILISATEUR(ID);

ALTER TABLE PRONOSTIC ADD CONSTRAINT PRONOSTIC_GAME_FK FOREIGN KEY(game_id) REFERENCES GAME(ID);