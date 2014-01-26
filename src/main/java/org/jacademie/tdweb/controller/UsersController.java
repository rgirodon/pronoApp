package org.jacademie.tdweb.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.NotificationDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
import org.jacademie.tdweb.service.MailService;
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
@SessionAttributes(value={"user","league","notificationDTO"})
public class UsersController {

	private static Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MessageSource messageSource;
	
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
		
		Locale locale = LocaleContextHolder.getLocale();
		
		Collection<String> errors = new ArrayList<>();
		
		if (StringUtils.isEmpty(notificationDTO.getSubject())) {
			
			errors.add(this.messageSource.getMessage("notify.users.subject.mandatory", 
					null, 
					locale));
		}
		
		if (StringUtils.isEmpty(notificationDTO.getText())) {
			
			errors.add(this.messageSource.getMessage("notify.users.text.mandatory", 
					null, 
					locale));
		}
		
		if (!errors.isEmpty()) {
		
			model.addAttribute("errors", errors);
		}
		else {
			this.mailService.sendLeagueUsersNotification(league.getId(), notificationDTO);
			
			model.addAttribute("actionMessage",
					this.messageSource.getMessage("notify.users.success", 
													null, 
													locale));
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
		
		Locale locale = LocaleContextHolder.getLocale();
		
		// get leagueId in session
		userService.reComputeRankingForLeague(league.getId());
		
		model.addAttribute("actionMessage",
				this.messageSource.getMessage("users.recompute.ranking.success", 
												null, 
												locale));
		
		return this.prepareUsersDisplay(league, model);
	}
	
	@RequestMapping(value="RemoveFromLeagueAdmins", method = RequestMethod.GET)
	public String removeFromLeagueAdmins(@RequestParam Integer userId,
										 @ModelAttribute League league,
										  ModelMap model) {
		
		logger.debug("RemoveFromLeagueAdmins : " + userId);
		
		Locale locale = LocaleContextHolder.getLocale();
		
		userService.removeFromLeagueAdmins(userId, league.getId());
		
		model.addAttribute("actionMessage",
				this.messageSource.getMessage("users.removeFromAdmins.success", 
												null, 
												locale));
		
		return this.prepareUsersDisplay(league, model);
	}
	
	@RequestMapping(value="AddToLeagueAdmins", method = RequestMethod.GET)
	public String addToLeagueAdmins(@RequestParam Integer userId,
									@ModelAttribute League league,
									ModelMap model) {
		
		logger.debug("AddToLeagueAdmins : " + userId);
		
		Locale locale = LocaleContextHolder.getLocale();
		
		userService.addToLeagueAdmins(userId, league.getId());
		
		model.addAttribute("actionMessage",
				this.messageSource.getMessage("users.addToAdmins.success", 
												null, 
												locale));
		
		return this.prepareUsersDisplay(league, model);
	}
}
