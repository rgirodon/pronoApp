package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.Game;

public interface GameService {

	Collection<Game> retrieveOpenedGames();

	Collection<Game> retrieveClosedGames();

	Collection<Game> retrieveGames();

	void closeGame(Integer id);

	void openGame(Integer id);

	void computePointsForGame(Integer id);

	void deleteGame(Integer id);

	boolean canDeleteGame(Integer id);

	Game retrieveGameById(Integer id);

	void updateGame(Game gameEdited);

	void createGame(Game gameBeingCreated);

	Collection<Game> retrievePointsComputedGames();

}
