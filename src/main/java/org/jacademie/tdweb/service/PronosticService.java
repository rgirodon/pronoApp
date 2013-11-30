package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.Pronostic;
import org.jacademie.tdweb.dto.GameForBetDTO;

public interface PronosticService {

	Collection<GameForBetDTO> retrieveGamesForBetForUser(Integer id);

	void saveBetsForUser(Integer id, Collection<GameForBetDTO> bets);

	Collection<GameForBetDTO> retrieveBetGamesForUser(Integer userId);

	Collection<Pronostic> retrievePronosticsForGame(Integer id);

}
