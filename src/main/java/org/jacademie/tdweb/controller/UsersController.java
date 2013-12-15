package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Users")
public class UsersController {

	private static Logger logger = Logger.getLogger(UsersController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="List", method = RequestMethod.GET)
	public String listHandler(ModelMap model) {
		
		logger.debug("List users.");
		
		return this.prepareUsersDisplay(model);
	}
	
	private String prepareUsersDisplay(ModelMap model) {
		
		Collection<User> users = userService.retrieveUsers();
		
		model.addAttribute("users", users);
		
		return "Users";
	}
	
	@RequestMapping(value="ReComputeRanking", method = RequestMethod.GET)
	public String reComputeRankingHandler(ModelMap model) {
		
		logger.debug("Re-computing ranking for users.");
		
		userService.reComputeRanking();
		
		model.addAttribute("actionMessage", "Ranking successfully re-computed.");
		
		return this.prepareUsersDisplay(model);
	}
}
