package org.jacademie.tdweb.service.impl;

import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.GameDao;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.Pronostic;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.service.GameService;
import org.jacademie.tdweb.service.LeagueService;
import org.jacademie.tdweb.service.PronosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class GameServiceImpl implements GameService {

	private static Logger logger = Logger.getLogger(GameServiceImpl.class);
	
	@Autowired
	private GameDao gameDao;
	
	@Autowired
	private PronosticService pronosticService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Override
	public Collection<Game> retrieveOpenedGamesForLeague(Integer leagueId) {
		
		return this.gameDao.retrieveOpenedGamesForLeague(leagueId);
	}
	
	@Override
	public Collection<Game> retrieveGamesForLeague(Integer leagueId) {
		
		return this.gameDao.retrieveGamesForLeague(leagueId);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void closeGame(Integer id) {
		
		Game game = this.retrieveGameById(id);
		
		game.setClosed(Boolean.TRUE);
	}

	@Override
	public Game retrieveGameById(Integer id) {
		
		return this.gameDao.retrieveGameById(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void openGame(Integer id) {
		
		Game game = this.retrieveGameById(id);
		
		game.setClosed(Boolean.FALSE);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void computePointsForGame(Integer id) {
		
		logger.debug("Computing points for game : " + id);
		
		Game game = this.retrieveGameById(id);
		
		Collection<Integer> leagueIds = new HashSet<>();
		
		Integer gameLeagueId = game.getLeague().getId(); 
		
		leagueIds.add(gameLeagueId);
		
		Collection<League> leaguesInheritingFromLeague = this.leagueService.retrieveLeaguesInheritingFromLeague(gameLeagueId);
		
		for (League leagueInheritingFromLeague : leaguesInheritingFromLeague) {
			
			leagueIds.add(leagueInheritingFromLeague.getId());
		}
		
		Collection<Pronostic> pronostics = this.pronosticService.retrievePronosticsForGame(id);
		
		logger.debug("Found " + pronostics.size() + " pronostics for this game");
		
		for (Pronostic pronostic : pronostics) {
			
			Integer points = this.determinePointsForPronosticWithGame(pronostic, game);
			
			pronostic.setPoints(points);
			
			User user = pronostic.getUser();
			
			for (Integer leagueId : leagueIds) {
			
				if (user.isInvolvedInLeague(leagueId)) {
				
					user.addPointsForLeague(leagueId, points);
					
					logger.debug("User " + user + " won " + points + " points for this game");
					
					user.incrementNbComputedPronosForLeague(leagueId);
					
					if (points.equals(1)) {
						
						user.incrementNbCorrectResultsForLeague(leagueId);
					}
					
					if (points.equals(3)) {
						
						user.incrementNbCorrectResultsForLeague(leagueId);
						
						user.incrementNbExactScoresForLeague(leagueId);
					}
				}
			}
		}
		
		game.setPointsComputed(Boolean.TRUE);
	}

	private Integer determinePointsForPronosticWithGame(Pronostic pronostic,
			Game game) {
		
		Integer result = 0;
		
		// case of exact result
		if (pronostic.getScoreTeam1().equals(game.getScoreTeam1())
				&& pronostic.getScoreTeam2().equals(game.getScoreTeam2())) {
			
			result = 3;
		}
		else {		
			// case team1 won
			if (game.getScoreTeam1() > game.getScoreTeam2()
					&& pronostic.getScoreTeam1() > pronostic.getScoreTeam2()) {
				
				result = 1;
			}
			
			// case team2 won 
			if (game.getScoreTeam2() > game.getScoreTeam1()
					&& pronostic.getScoreTeam2() > pronostic.getScoreTeam1()) {
				
				result = 1;
			}
			
			// case draw
			if (game.getScoreTeam2().equals(game.getScoreTeam1())
					&& pronostic.getScoreTeam2().equals(pronostic.getScoreTeam1())) {
				
				result = 1;
			}
		}		
		
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteGame(Integer id) {
		
		logger.debug("Deleting game : " + id);
		
		this.gameDao.deleteGame(id);
	}

	@Override
	public boolean canDeleteGame(Integer id) {
		
		return this.pronosticService.retrievePronosticsForGame(id).isEmpty();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateGame(Game gameEdited) {
		
		logger.debug("Updating game : " + gameEdited.getId());
		
		this.gameDao.updateGame(gameEdited);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createGame(Game gameBeingCreated) {
		
		logger.debug("Creating game : " + gameBeingCreated);
		
		this.gameDao.createGame(gameBeingCreated);
	}

	@Override
	public Collection<Game> retrievePointsComputedGamesForLeague(Integer leagueId) {
		
		return this.gameDao.retrievePointsComputedGamesForLeague(leagueId);
	}
	
	@Override
	public Collection<Game> retrievePointsNotComputedClosedGamesForLeague(Integer leagueId) {
		
		return this.gameDao.retrievePointsNotComputedClosedGamesForLeague(leagueId);
	}
}
