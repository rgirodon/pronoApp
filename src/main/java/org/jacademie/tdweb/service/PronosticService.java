package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.Pronostic;
import org.jacademie.tdweb.dto.GameForBetDTO;

public interface PronosticService {

	Collection<GameForBetDTO> retrieveGamesForBetForUserForLeague(Integer userId, Integer leagueId);

	void saveBetsForUser(Integer id, Collection<GameForBetDTO> bets);

	Collection<GameForBetDTO> retrieveComputedBetGamesForUserForLeague(Integer userId, Integer leagueId);

	Collection<Pronostic> retrievePronosticsForGame(Integer id);

	Collection<GameForBetDTO> retrieveNotComputedBetGamesForUserForLeague(Integer userId, Integer leagueId);

	Collection<GameForBetDTO> retrieveOthersPronosticsForGame(Integer idUser,
			Integer idGame);

}
