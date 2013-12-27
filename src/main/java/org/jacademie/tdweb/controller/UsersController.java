package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
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
@SessionAttributes(value={"league"})
public class UsersController {

	private static Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="ListUsers", method = RequestMethod.GET)
	public String listHandler(@ModelAttribute League league,
							  ModelMap model) {
		
		logger.debug("List users.");
		
		return this.prepareUsersDisplay(league, model);
	}
	
	private String prepareUsersDisplay(League league, ModelMap model) {
		
		Collection<User> users = userService.retrieveUsersForLeague(league.getId());
		
		model.addAttribute("users", users);
		
		return "Users";
	}
	
	@RequestMapping(value="ReComputeRanking", method = RequestMethod.GET)
	public String reComputeRankingHandler(@ModelAttribute League league,
										  ModelMap model) {
		
		logger.debug("Re-computing ranking for users.");
		
		// get leagueId in session
		userService.reComputeRankingForLeague(league.getId());
		
		model.addAttribute("actionMessage", "Ranking successfully re-computed.");
		
		return this.prepareUsersDisplay(league, model);
	}
	
	@RequestMapping(value="RemoveFromLeagueAdmins", method = RequestMethod.GET)
	public String removeFromLeagueAdmins(@RequestParam Integer userId,
										 @ModelAttribute League league,
										  ModelMap model) {
		
		logger.debug("RemoveFromLeagueAdmins : " + userId);
		
		userService.removeFromLeagueAdmins(userId, league.getId());
		
		model.addAttribute("actionMessage", "User removed from league admins.");
		
		return this.prepareUsersDisplay(league, model);
	}
	
	@RequestMapping(value="AddToLeagueAdmins", method = RequestMethod.GET)
	public String addToLeagueAdmins(@RequestParam Integer userId,
									@ModelAttribute League league,
									ModelMap model) {
		
		logger.debug("AddToLeagueAdmins : " + userId);
		
		userService.addToLeagueAdmins(userId, league.getId());
		
		model.addAttribute("actionMessage", "User added to league admins.");
		
		return this.prepareUsersDisplay(league, model);
	}
}
