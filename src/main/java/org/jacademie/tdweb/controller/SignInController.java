package org.jacademie.tdweb.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user","league"})
@RequestMapping("/SignIn")
public class SignInController {

	private static Logger logger = Logger.getLogger(SignInController.class);
	
	@Autowired
	private WelcomeController welcomeController;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HelloHelper helloHelper;
	
	@RequestMapping(method = RequestMethod.GET)
	public String displaySignInHandler(ModelMap model) {
		
		logger.debug("In displaySignInHandler");
		
		return "SignIn";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateSignInHandler(HttpSession session, 
										@ModelAttribute LoginPasswordDTO loginPasswordDTO, 
										ModelMap model) {
		
		logger.debug("In validateSignInHandler");
		
		logger.debug("login : " + loginPasswordDTO.getLogin());
		
		logger.debug("password : " + loginPasswordDTO.getPassword());
		
		if (this.userService.validateSignIn(loginPasswordDTO)) {
		
			logger.debug("Login / Password is valid");
			
			User user = this.userService.findUserByLogin(loginPasswordDTO.getLogin());
			
			model.addAttribute("user", user);
			
			if (user.getDefaultLeague() == null) {
			
				Integer nbInvitations = this.userService.retrieveInvitationsForUser(user.getId()).size();
				
				model.addAttribute("nbInvitations", nbInvitations);
				
				return "Welcome";
			}
			else {
				return welcomeController.chooseLeagueHandler(session, user.getDefaultLeague().getId(), user, model);
			}
		}
		else {
			logger.debug("Login / Password is not valid");
			
			model.addAttribute("badLogin", Boolean.TRUE);
			
			return "SignIn";
		}
    }
}
