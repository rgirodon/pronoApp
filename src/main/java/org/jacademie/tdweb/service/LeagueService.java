package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.League;

public interface LeagueService {

	League findLeagueById(Integer leagueId);

	Collection<League> retrievePublicLeaguesAvailableForUser(Integer userId);

	void createLeague(League league, Integer userId);

	Collection<String> validateLeague(League league);

	void saveLeague(League leagueBeingEdited);

	Collection<League> retrieveLeaguesInheritingFromLeague(Integer leagueId);

	Collection<League> retrievePublicLeagues();

}
