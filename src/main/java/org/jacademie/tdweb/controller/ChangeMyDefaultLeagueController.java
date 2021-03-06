package org.jacademie.tdweb.controller;

import java.util.Collection;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
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
@SessionAttributes(value={"user"})
@RequestMapping("/ChangeMyDefaultLeague")
public class ChangeMyDefaultLeagueController {

	private static Logger logger = Logger.getLogger(ChangeMyDefaultLeagueController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
		
	@RequestMapping(method = RequestMethod.GET)
	public String displayChangeMyDefaultLeagueHandler(ModelMap model) {
		
		logger.debug("In displayChangeMyDefaultLeagueHandler");
		
		return "ChangeMyDefaultLeague";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateChangeMyDefaultLeagueHandler(@RequestParam Integer defaultLeagueId, 
													@ModelAttribute(value = "user") User user, 
													ModelMap model) {
		
		logger.debug("In validateChangeMyDefaultLeagueHandler");
		
		Locale locale = LocaleContextHolder.getLocale();
		
		this.userService.changeMyDefaultLeague(user.getId(), defaultLeagueId);
		
		model.addAttribute("changeMyDefaultLeagueInformation", this.messageSource.getMessage("myDefaultLeague.success", 
																							null, 
																							locale));
		
		// refresh user
		user = this.userService.findUserById(user.getId());
		
		model.addAttribute("user", user);
		
		return "ChangeMyDefaultLeague";
    }
}
