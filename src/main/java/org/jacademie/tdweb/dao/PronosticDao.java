package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.Pronostic;

public interface PronosticDao {

	Pronostic retrievePronosticForGameAndUser(Integer gameId, Integer userId);

	void save(Pronostic pronostic);

	Collection<Pronostic> retrievePronosticsForGame(Integer id);

}
