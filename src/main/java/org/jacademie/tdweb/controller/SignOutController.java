package org.jacademie.tdweb.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
@RequestMapping("/SignOut")
public class SignOutController {

	private static Logger logger = Logger.getLogger(SignOutController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String signOutHandler(HttpSession session) {
		
		logger.debug("In signOutHandler");
		
		String login = (String)session.getAttribute("login");
		
		logger.debug("User is signing out : " + login);
		
		session.invalidate();
		
		return "SignIn";
    }
	
	
}
