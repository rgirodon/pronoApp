package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.dto.GameForBetDTO;

public interface PronosticService {

	Collection<GameForBetDTO> retrieveGamesForBetForUser(Integer id);

	void saveBetsForUser(Integer id, Collection<GameForBetDTO> bets);

}
