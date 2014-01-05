package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyDisplayNameDTO;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user","changeMyDisplayNameDTO"})
@RequestMapping("/ChangeMyDisplayName")
public class ChangeMyDisplayNameController {

	private static Logger logger = Logger.getLogger(ChangeMyDisplayNameController.class);
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(method = RequestMethod.GET)
	public String displayChangeMyDisplayNameHandler(@ModelAttribute(value = "user") User user, 
													ModelMap model) {
		
		logger.debug("In displayChangeMyDisplayNameHandler");
		
		ChangeMyDisplayNameDTO changeMyDisplayNameDTO = new ChangeMyDisplayNameDTO();
		changeMyDisplayNameDTO.setDisplayNameInput(user.getDisplayName());
		
		model.addAttribute("changeMyDisplayNameDTO", changeMyDisplayNameDTO);
		
		return "ChangeMyDisplayName";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateChangeMyDisplayNameHandler(@ModelAttribute ChangeMyDisplayNameDTO changeMyDisplayNameDTO, 
													@ModelAttribute(value = "user") User user, 
													ModelMap model) {
		
		logger.debug("In validateChangeMyDisplayNameHandler");
		
		logger.debug("new displayName : " + changeMyDisplayNameDTO.getDisplayNameInput());
		
		Collection<String> errors = this.userService.validateChangeMyDisplayName(user.getId(), changeMyDisplayNameDTO);
		
		if (errors.isEmpty()) {
		
			logger.debug("ChangeMyDisplayName is valid");
			
			this.userService.changeMyDisplayName(user.getId(), changeMyDisplayNameDTO);
			
			model.addAttribute("changeMyDisplayNameInformation", "Display name successfully changed");
			
			// refresh user
			user = this.userService.findUserById(user.getId());
			
			model.addAttribute("user", user);
			
			return "ChangeMyDisplayName";
		}
		else {
			logger.debug("ChangeMyDisplayName is not valid");
			
			model.addAttribute("errors", errors);
			
			return "ChangeMyDisplayName";
		}
    }
}
