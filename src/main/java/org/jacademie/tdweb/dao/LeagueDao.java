package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.League;

public interface LeagueDao {

	League findLeagueById(Integer leagueId);

	Collection<League> retrievePublicLeagues();

	void createLeague(League league);

	Collection<League> findLeaguesByName(String name);

	void saveLeague(League league);

	Collection<League> retrieveLeaguesInheritingFromLeague(Integer leagueId);

}
