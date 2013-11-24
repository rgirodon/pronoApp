package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.Game;

public interface GameService {

	Collection<Game> retrieveOpenedGames();

	Collection<Game> retrieveClosedGames();

}
