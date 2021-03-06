package org.jacademie.tdweb.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.service.PronosticService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user","league","gamesForBet"})
@RequestMapping("/Bet")
public class BetController {

	private static Logger logger = Logger.getLogger(BetController.class);
	
	@Autowired
	private HelloHelper helloHelper;
	
	@Autowired
	private UserService userService;

	@Autowired
	private PronosticService pronosticService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.POST)
	public String betHandler(@RequestParam Map<String,String> allRequestParams, 
							 @ModelAttribute(value = "user") User user, 
							 @ModelAttribute(value = "league") League league,
							 @ModelAttribute(value = "gamesForBet") Collection<GameForBetDTO> gamesForBet, 
							 ModelMap model) {
		
		logger.debug("User " + user.getLogin() + " is betting.");

		Locale locale = LocaleContextHolder.getLocale();
		
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
			
			Integer [] messageParams = {validBets.size()};
			
			model.addAttribute("betConfirmMessage",
					this.messageSource.getMessage("bet.valid", 
										messageParams, 
										locale));
		}
		
		if (!invalidBets.isEmpty()) {
			
			Integer [] messageParams = {invalidBets.size()};
			
			model.addAttribute("betErrorMessage",
					this.messageSource.getMessage("bet.invalid", 
										messageParams, 
										locale));
		}
		
		// get leagueId
		helloHelper.prepareHelloDisplay(user.getId(), league.getId(), model);
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		return "Hello";
	}
}
