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
	
	public void prepareHelloDisplay(Integer userId, ModelMap model) {
	
		Integer ranking = this.userService.determineRankingForUser(userId);
		
		model.addAttribute("ranking", ranking);
		
		Integer nbTotalUsers = this.userService.determineNbTotalUsers();
		
		model.addAttribute("nbTotalUsers", nbTotalUsers);
		
		Collection<GameForBetDTO> gamesForBet = this.pronosticService.retrieveGamesForBetForUser(userId);
		
		model.addAttribute("gamesForBet", gamesForBet);
		
		Collection<GameForBetDTO> notComputedBetGames = this.pronosticService.retrieveNotComputedBetGamesForUser(userId);
		
		model.addAttribute("notComputedBetGames", notComputedBetGames);
		
		Collection<GameForBetDTO> computedBetGames = this.pronosticService.retrieveComputedBetGamesForUser(userId);
		
		model.addAttribute("computedBetGames", computedBetGames);
		
		Collection<User> rankingUsers = this.userService.retrieveRankingUsers();
		
		model.addAttribute("rankingUsers", rankingUsers);
	}
}
