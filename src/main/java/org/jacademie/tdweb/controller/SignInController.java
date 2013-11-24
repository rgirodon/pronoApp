package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.service.PronosticService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"login"})
@RequestMapping("/SignIn")
public class SignInController {

	private static Logger logger = Logger.getLogger(SignInController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PronosticService pronosticService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String displaySignInHandler(ModelMap model) {
		
		logger.debug("In displaySignInHandler");
		
		return "SignIn";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateSignInHandler(@ModelAttribute LoginPasswordDTO loginPasswordDTO, ModelMap model) {
		
		logger.debug("In validateSignInHandler");
		
		logger.debug("login : " + loginPasswordDTO.getLogin());
		
		logger.debug("password : " + loginPasswordDTO.getPassword());
		
		if (this.userService.validateSignIn(loginPasswordDTO)) {
		
			logger.debug("Login / Password is valid");
			
			User user = this.userService.findUserByLogin(loginPasswordDTO.getLogin());
			
			model.addAttribute("user", user);
			
			Integer ranking = this.userService.determineRankingForUser(user.getId());
			
			model.addAttribute("ranking", ranking);
			
			Integer nbTotalUsers = this.userService.determineNbTotalUsers();
			
			model.addAttribute("nbTotalUsers", nbTotalUsers);
			
			Collection<GameForBetDTO> gamesForBet = this.pronosticService.retrieveGamesForBetForUser(user.getId());
			
			model.addAttribute("gamesForBet", gamesForBet);
			
			return "Hello";
		}
		else {
			logger.debug("Login / Password is not valid");
			
			model.addAttribute("badLogin", Boolean.TRUE);
			
			return "SignIn";
		}
    }
}
