package org.jacademie.tdweb.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.NotificationDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
import org.jacademie.tdweb.service.MailService;
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
@SessionAttributes(value={"user","league","notificationDTO"})
public class UsersController {

	private static Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="NotifyLeagueUsers", method = RequestMethod.GET)
	public String displayNotifyLeagueUsersHandler(@ModelAttribute League league,
							  ModelMap model) {
		
		logger.debug("Display NotifyLeagueUsers.");
		
		NotificationDTO notificationDTO = new NotificationDTO();
		
		model.addAttribute("notificationDTO", notificationDTO);
		
		return "NotifyLeagueUsers";
	}
	
	@RequestMapping(value="NotifyLeagueUsers", method = RequestMethod.POST)
	public String submitNotifyLeagueUsersHandler(@ModelAttribute League league,
												  @ModelAttribute NotificationDTO notificationDTO,
												  ModelMap model) {
		
		logger.debug("Submit NotifyLeagueUsersHandler.");
		
		Collection<String> errors = new ArrayList<>();
		
		if (StringUtils.isEmpty(notificationDTO.getSubject())) {
			
			errors.add("Subject is mandatory");
		}
		
		if (StringUtils.isEmpty(notificationDTO.getText())) {
			
			errors.add("Text is mandatory");
		}
		
		if (!errors.isEmpty()) {
		
			model.addAttribute("errors", errors);
		}
		else {
			this.mailService.sendLeagueUsersNotification(league.getId(), notificationDTO);
			
			model.addAttribute("actionMessage", "Notification sent to League Users");
		}
		
		return "NotifyLeagueUsers";
	}
	
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
