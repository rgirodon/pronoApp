package org.jacademie.tdweb.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.service.PronosticService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user","gamesForBet"})
@RequestMapping("/Bet")
public class BetController {

	private static Logger logger = Logger.getLogger(BetController.class);
	
	@Autowired
	private HelloHelper helloHelper;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PronosticService pronosticService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String betHandler(@RequestParam Map<String,String> allRequestParams, 
							 @ModelAttribute(value = "user") User user, 
							 @ModelAttribute(value = "gamesForBet") Collection<GameForBetDTO> gamesForBet, 
							 ModelMap model) {
		
		logger.debug("User " + user.getLogin() + " is betting.");

		Collection<GameForBetDTO> validBets = new ArrayList<>();
		Collection<GameForBetDTO> invalidBets = new ArrayList<>();
		
		for (GameForBetDTO gameForBet : gamesForBet) {
			
			Integer idGame = gameForBet.getIdGame();
			
			String scoreTeam1 = allRequestParams.get(idGame + "_scoreTeam1");
			String scoreTeam2 = allRequestParams.get(idGame + "_scoreTeam2");
			
			if (NumberUtils.isDigits(scoreTeam1)
					&& NumberUtils.isDigits(scoreTeam2)) {
				
				logger.debug("Bet for game " + gameForBet.getGame() + " is valid.");
				
				gameForBet.setScoreTeam1(Integer.parseInt(scoreTeam1));
				gameForBet.setScoreTeam2(Integer.parseInt(scoreTeam2));
				
				validBets.add(gameForBet);
			}
			else {
				logger.debug("Bet for game " + gameForBet.getGame() + " is not valid.");
				
				invalidBets.add(gameForBet);
			}
		}
		
		logger.debug("Found " + validBets.size() + " valid bets.");
		
		if (!validBets.isEmpty()) {
			
			pronosticService.saveBetsForUser(user.getId(), validBets);
			
			model.addAttribute("betConfirmMessage", "You have saved " + validBets.size() + " bet(s). Thanks and good luck !");
		}
		
		if (!invalidBets.isEmpty()) {
			
			model.addAttribute("betErrorMessage", invalidBets.size() + " of your bet(s) were invalid. Retry !");
		}
		
		helloHelper.prepareHelloDisplay(user.getId(), model);
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		return "Hello";
	}
}
