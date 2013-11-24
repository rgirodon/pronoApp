package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.Game;

public interface GameDao {

	Collection<Game> retrieveOpenedGames();

}
