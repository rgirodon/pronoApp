package org.jacademie.tdweb.controller.helper;

import java.util.Collection;

import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.service.PronosticService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class HelloHelper {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PronosticService pronosticService;
	
	public void prepareHelloDisplay(Integer userId, Integer leagueId, ModelMap model) {
	
		Integer ranking = this.userService.determineRankingForUserForLeague(userId, leagueId);
		
		model.addAttribute("ranking", ranking);
		
		Integer nbTotalUsers = this.userService.determineNbTotalUsersForLeague(leagueId);
		
		model.addAttribute("nbTotalUsers", nbTotalUsers);
		
		Collection<GameForBetDTO> gamesForBet = this.pronosticService.retrieveGamesForBetForUserForLeague(userId, leagueId);
		
		model.addAttribute("gamesForBet", gamesForBet);
		
		Collection<GameForBetDTO> notComputedBetGames = this.pronosticService.retrieveNotComputedBetGamesForUserForLeague(userId, leagueId);
		
		model.addAttribute("notComputedBetGames", notComputedBetGames);
		
		Collection<GameForBetDTO> computedBetGames = this.pronosticService.retrieveComputedBetGamesForUserForLeague(userId, leagueId);
		
		model.addAttribute("computedBetGames", computedBetGames);
		
		Collection<User> rankingUsers = this.userService.retrieveRankingUsersForLeague(leagueId);
		
		model.addAttribute("rankingUsers", rankingUsers);
	}
}
