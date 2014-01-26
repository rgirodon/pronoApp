package org.jacademie.tdweb.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.Invitation;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.service.LeagueService;
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
@SessionAttributes(value={"user","league"})
public class InvitationsController {

private static Logger logger = Logger.getLogger(InvitationsController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private MessageSource messageSource;
			
	@RequestMapping(value="MyInvitations", method = RequestMethod.GET)
	public String displayMyInvitationsHandler(@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayMyInvitationsHandler");
				
		Collection<Invitation> myInvitations = this.userService.retrieveInvitationsForUser(user.getId());
		
		model.addAttribute("myInvitations", myInvitations);
		
		return "MyInvitations";
	}
	
	@RequestMapping(value="DeclineInvitation", method = RequestMethod.GET)
	public String displayDeclineInvitationHandler(@RequestParam Integer invitationId,
												@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayDeclineInvitationHandler");
		
		Locale locale = LocaleContextHolder.getLocale();
				
		this.userService.declineInvitation(invitationId);
		
		model.addAttribute("actionMessage", this.messageSource.getMessage("myInvitations.declined", 
																			null, 
																			locale));
		
		return this.displayMyInvitationsHandler(user, model);
	}
	
	@RequestMapping(value="AcceptInvitation", method = RequestMethod.GET)
	public String displayAcceptInvitationHandler(@RequestParam Integer invitationId,
												@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayAcceptInvitationHandler");
		
		Locale locale = LocaleContextHolder.getLocale();
				
		this.userService.acceptInvitation(invitationId, user.getId());
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		model.addAttribute("actionMessage", this.messageSource.getMessage("myInvitations.accepted", 
																			null, 
																			locale));
		
		return this.displayMyInvitationsHandler(user, model);
	}
	
	@RequestMapping(value="InviteFriends", method = RequestMethod.GET)
	public String displayInviteFriendsHandler(@ModelAttribute User user,
												ModelMap model) {
		
		logger.debug("In displayInviteFriendsHandler");
				
		return "InviteFriends";
	}
	
	@RequestMapping(value="InviteFriends", method = RequestMethod.POST)
	public String inviteFriendsActionHandler(@RequestParam String friend1,
											@RequestParam String friend2,
											@RequestParam String friend3,
											@RequestParam String friend4,
											@RequestParam String friend5,
											@ModelAttribute User user,
											@ModelAttribute(value="league") League league,
											ModelMap model) {
		
		logger.debug("In inviteFriendsActionHandler");
				
		Locale locale = LocaleContextHolder.getLocale();
		
		Collection<String> errors = new HashSet<>();
		
		Collection<String> friends = new HashSet<>();
		
		if (!StringUtils.isEmpty(friend1)) {
			friends.add(friend1);
		}
		if (!StringUtils.isEmpty(friend2)) {
			friends.add(friend2);
		}
		if (!StringUtils.isEmpty(friend3)) {
			friends.add(friend3);
		}
		if (!StringUtils.isEmpty(friend4)) {
			friends.add(friend4);
		}
		if (!StringUtils.isEmpty(friend5)) {
			friends.add(friend5);
		}
		
		int nbInvitations = 0;
		
		for (String friend : friends) {
			
			if (this.userService.checkInvitationIsValid(friend)) {
				
				if (!this.userService.checkUserIsInvolvedInLeague(friend, league.getId())) {
					
					if (!this.userService.checkUserIsInvitedToLeague(friend, league.getId())) {
						
						this.userService.inviteFriend(friend, user.getId(), league.getId());
						
						nbInvitations++;
					}
					else {
						errors.add(friend + 
								this.messageSource.getMessage("myInvitations.alreadyInvited", null, locale) + 
								league.getName());
					}
				}
				else {
					errors.add(friend + 
								this.messageSource.getMessage("myInvitations.alreadyJoined", null, locale) + 
								league.getName());
				}
			}
			else {
				errors.add(friend + 
							this.messageSource.getMessage("myInvitations.invalid", null, locale));
			}
		}
		
		if (!errors.isEmpty()) {
			
			model.addAttribute("errors", errors);
		}
		
		model.addAttribute("actionMessage", nbInvitations + 
											this.messageSource.getMessage("myInvitations.sent", null, locale));		
		
		return "InviteFriends";
	}
}
