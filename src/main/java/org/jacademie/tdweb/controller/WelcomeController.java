package org.jacademie.tdweb.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.service.LeagueService;
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
@SessionAttributes(value={"user", "league","gamesForBet"})
public class WelcomeController {

	private static Logger logger = Logger.getLogger(WelcomeController.class);
	
	@Autowired
	private HelloHelper helloHelper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueService leagueService;
	
	@RequestMapping(value="/Welcome", method = RequestMethod.GET)
	public String welcomeHandler(HttpSession session,
								 @ModelAttribute(value = "user") User user, 
								 ModelMap model) {
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		Integer nbInvitations = this.userService.retrieveInvitationsForUser(user.getId()).size();
		
		model.addAttribute("nbInvitations", nbInvitations);
		
		model.remove("league");
		
	    session.removeAttribute("league");
		
		return "Welcome";
	}
	
	@RequestMapping(value="/ChooseLeague.do", method = RequestMethod.POST)
	public String chooseLeagueHandler(HttpSession session,
									  @RequestParam(value = "leagueId") Integer leagueId,
									  @ModelAttribute(value = "user") User user,
									  ModelMap model) {
		
		logger.debug("In chooseLeagueHandler with param : " + leagueId);
		
		if (leagueId != -1) {
			// refresh league
			League league = this.leagueService.findLeagueById(leagueId);
			
			model.addAttribute("league", league);
			
			helloHelper.prepareHelloDisplay(user.getId(), leagueId, model);
			
			return "Hello";
		}
		else {
			model.remove("league");
		    session.removeAttribute("league");
			
			return this.welcomeHandler(session, user, model);
		}
	}
}
