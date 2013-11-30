package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.Game;

public interface GameDao {

	Collection<Game> retrieveOpenedGames();

	Collection<Game> retrieveClosedGames();

	Collection<Game> retrieveGames();

	Game retrieveGameById(Integer id);

	void deleteGame(Integer id);

}
