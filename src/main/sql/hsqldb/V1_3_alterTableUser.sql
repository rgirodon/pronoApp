ALTER TABLE UTILISATEUR ADD NB_CORRECT_RESULTS INTEGER;
ALTER TABLE UTILISATEUR ADD NB_EXACT_SCORES INTEGER;
ALTER TABLE UTILISATEUR ADD NB_COMPUTED_PRONOS INTEGER;

UPDATE UTILISATEUR SET NB_CORRECT_RESULTS = 0;
UPDATE UTILISATEUR SET NB_EXACT_SCORES = 0;
UPDATE UTILISATEUR SET NB_COMPUTED_PRONOS = 0;