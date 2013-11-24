package org.jacademie.tdweb.service.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.GameDao;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

	private static Logger logger = Logger.getLogger(GameServiceImpl.class);
	
	@Autowired
	private GameDao gameDao;
	
	@Override
	public Collection<Game> retrieveOpenedGames() {
		
		return this.gameDao.retrieveOpenedGames();
	}

}
