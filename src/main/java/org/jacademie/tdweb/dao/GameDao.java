package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.Game;

public interface GameDao {

	Collection<Game> retrieveOpenedGamesForLeague(Integer leagueId);

	Collection<Game> retrieveGamesForLeague(Integer leagueId);

	Game retrieveGameById(Integer id);

	void deleteGame(Integer id);

	void updateGame(Game gameEdited);

	void createGame(Game gameBeingCreated);

	Collection<Game> retrievePointsComputedGamesForLeague(Integer leagueId);

	Collection<Game> retrievePointsNotComputedClosedGamesForLeague(Integer leagueId);

}
