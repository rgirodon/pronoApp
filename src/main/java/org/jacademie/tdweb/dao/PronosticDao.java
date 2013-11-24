package org.jacademie.tdweb.dao;

import org.jacademie.tdweb.domain.Pronostic;

public interface PronosticDao {

	Pronostic retrievePronosticForGameAndUser(Integer gameId, Integer userId);

}
