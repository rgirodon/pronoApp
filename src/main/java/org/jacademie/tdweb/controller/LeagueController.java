package org.jacademie.tdweb.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
@SessionAttributes(value={"user","league","gamesForBet","leagueBeingCreated","leagueBeingEdited"})
public class LeagueController {

private static Logger logger = Logger.getLogger(LeagueController.class);
	
	@Autowired
	private WelcomeController welcomeController;

	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueService leagueService;
		
	@RequestMapping(value="CreatePublicLeague", method = RequestMethod.GET)
	public String displayCreatePublicLeagueHandler(@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayCreatePublicLeagueHandler");
		
		League leagueBeingCreated = new League();
		leagueBeingCreated.setIsPublic(Boolean.TRUE);
		
		model.addAttribute("leagueBeingCreated", leagueBeingCreated);
		
		return "CreateLeague";
	}
	
	@RequestMapping(value="CreatePrivateLeague", method = RequestMethod.GET)
	public String displayCreatePrivateLeagueHandler(@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayCreatePrivateLeagueHandler");
		
		League leagueBeingCreated = new League();
		leagueBeingCreated.setIsPublic(Boolean.FALSE);
		
		model.addAttribute("leagueBeingCreated", leagueBeingCreated);
		
		return "CreateLeague";
	}
	
	@RequestMapping(value="CreateLeague", method = RequestMethod.POST)
	public String createLeagueHandler(HttpSession session,
										@ModelAttribute User user,
										@ModelAttribute(value="leagueBeingCreated") League leagueBeingCreated,
										ModelMap model) {
		
		logger.debug("In createLeagueHandler");
		
		Collection<String> errors = this.leagueService.validateLeague(leagueBeingCreated);
		
		if (errors.isEmpty()) {
			
			logger.debug("League creation is valid");
			
			this.leagueService.createLeague(leagueBeingCreated, user.getId());
			
			// refresh user
			user = this.userService.findUserById(user.getId());
			
			model.addAttribute("user", user);
			
			return welcomeController.chooseLeagueHandler(session, leagueBeingCreated.getId(), user, model);
		}
		else {
			logger.debug("League creation is not valid");
			
			model.addAttribute("errors", errors);
			
			return "CreateLeague";
		}
	}
	
	@RequestMapping(value="EditLeague", method = RequestMethod.GET)
	public String displayEditLeagueHandler(@ModelAttribute User user,
										   @ModelAttribute League league,
											ModelMap model) {
		
		logger.debug("In displayEditLeagueHandler");
		
		League leagueBeingEdited = this.leagueService.findLeagueById(league.getId());
		
		model.addAttribute("leagueBeingEdited", leagueBeingEdited);
		
		return "EditLeague";
	}
	
	@RequestMapping(value="EditLeague", method = RequestMethod.POST)
	public String editLeagueHandler(HttpSession session,
										@ModelAttribute User user,
										@ModelAttribute(value="leagueBeingEdited") League leagueBeingEdited,
										ModelMap model) {
		
		logger.debug("In editLeagueHandler");
		
		Collection<String> errors = this.leagueService.validateLeague(leagueBeingEdited);
		
		if (errors.isEmpty()) {
			
			logger.debug("League edition is valid");
			
			this.leagueService.saveLeague(leagueBeingEdited);
			
			// refresh user
			user = this.userService.findUserById(user.getId());
			
			model.addAttribute("user", user);
			
			return welcomeController.chooseLeagueHandler(session, leagueBeingEdited.getId(), user, model);
		}
		else {
			logger.debug("League creation is not valid");
			
			model.addAttribute("errors", errors);
			
			return "EditLeague";
		}
	}
	
	
	
	@RequestMapping(value="JoinPublicLeague", method = RequestMethod.GET)
	public String displayJoinPublicLeagueHandler(@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayJoinPublicLeagueHandler");
		
		Collection<League> publicLeagues = this.leagueService.retrievePublicLeaguesAvailableForUser(user.getId());
				
		model.addAttribute("availablePublicLeagues", publicLeagues);
		
		return "JoinPublicLeague";
    }
	
	@RequestMapping(value="JoinPublicLeagueAction", method = RequestMethod.GET)
	public String joinPublicLeagueActionHandler(HttpSession session,
												@RequestParam Integer leagueId,
												@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In joinPublicLeagueActionHandler");
		
		League league = this.leagueService.findLeagueById(leagueId);
				
		this.userService.involveUserInLeague(user.getId(), league.getId());		
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		return welcomeController.chooseLeagueHandler(session, league.getId(), user, model);
    }
}
