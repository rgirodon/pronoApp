package org.jacademie.tdweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.PronosticDao;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.Pronostic;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.service.GameService;
import org.jacademie.tdweb.service.PronosticService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PronosticServiceImpl implements PronosticService {

	private static Logger logger = Logger.getLogger(PronosticServiceImpl.class);

	@Autowired
	private PronosticDao pronosticDao;
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private UserService userService;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveBetsForUser(Integer id, Collection<GameForBetDTO> bets) {
		
		User user = this.userService.findUserById(id);
		
		logger.debug("Saving bets for user : " + user);
		
		for (GameForBetDTO bet : bets) {
			
			this.pronosticDao.save(bet.getPronostic());
		}
	}
	
	@Override
	public Collection<GameForBetDTO> retrieveGamesForBetForUser(Integer id) {
		
		Collection<GameForBetDTO> result = new ArrayList<>();
		
		Collection<Game> openedGames = this.gameService.retrieveOpenedGames();
		
		logger.debug("Found opened games : " + openedGames.size());
		
		User user = this.userService.findUserById(id);
		
		logger.debug("Found user : " + user);
		
		for (Game openedGame : openedGames) {
			
			logger.debug("Found opened game : " + openedGame);
			
			GameForBetDTO gameForBetDTO = new GameForBetDTO();
			
			gameForBetDTO.setGame(openedGame);
			
			Pronostic userPronostic = this.pronosticDao.retrievePronosticForGameAndUser(openedGame.getId(), id);
			
			if (userPronostic == null) {
				
				logger.debug("Did not find a pronostic for this user and this game");
				
				userPronostic = new Pronostic();
				userPronostic.setPoints(0);
				userPronostic.setGame(openedGame);
				user.addPronostic(userPronostic);
			}
			else {
				logger.debug("Found a pronostic for this user and this game : " + userPronostic);
			}
			
			gameForBetDTO.setPronostic(userPronostic);
			
			result.add(gameForBetDTO);
		}
		
		return result;
	}
	
	@Override
	public Collection<GameForBetDTO> retrieveBetGamesForUser(Integer id) {
		
		Collection<GameForBetDTO> result = new ArrayList<>();
		
		Collection<Game> closedGames = this.gameService.retrieveClosedGames();
		
		logger.debug("Found closed games : " + closedGames.size());
		
		User user = this.userService.findUserById(id);
		
		logger.debug("Found user : " + user);
		
		for (Game closedGame : closedGames) {
			
			logger.debug("Found closed game : " + closedGame);
			
			Pronostic userPronostic = this.pronosticDao.retrievePronosticForGameAndUser(closedGame.getId(), id);
			
			if (userPronostic != null) {
				
				logger.debug("Found a pronostic for this user and this game : " + userPronostic);
				
				GameForBetDTO betGameDTO = new GameForBetDTO();
				
				betGameDTO.setGame(closedGame);
				
				betGameDTO.setPronostic(userPronostic);
				
				result.add(betGameDTO);
			}
		}
		
		return result;
	}
}
