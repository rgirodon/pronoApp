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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user"})
@RequestMapping("/ChangeMyPassword")
public class ChangeMyPasswordController {

	private static Logger logger = Logger.getLogger(ChangeMyPasswordController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageSource messageSource;
		
	@RequestMapping(method = RequestMethod.GET)
	public String displayChangeMyPasswordHandler(ModelMap model) {
		
		logger.debug("In displayChangeMyPasswordHandler");
		
		ChangeMyPasswordDTO changeMyPasswordDTO = new ChangeMyPasswordDTO();
				
		model.addAttribute("changeMyPasswordDTO", changeMyPasswordDTO);
		
		return "ChangeMyPassword";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateChangeMyPasswordHandler(@ModelAttribute ChangeMyPasswordDTO changeMyPasswordDTO, 
													@ModelAttribute(value = "user") User user, 
													ModelMap model) {
		
		logger.debug("In validateChangeMyPasswordHandler");
		
		Locale locale = LocaleContextHolder.getLocale();
		
		logger.debug("old password : " + changeMyPasswordDTO.getOldPassword());
		
		logger.debug("new password : " + changeMyPasswordDTO.getNewPassword());
		
		logger.debug("re-enter new password : " + changeMyPasswordDTO.getReEnterNewPassword());
		
		Collection<String> errors = this.userService.validateChangeMyPassword(user.getId(), changeMyPasswordDTO);
		
		if (errors.isEmpty()) {
		
			logger.debug("ChangeMyPassword is valid");
			
			this.userService.changeMyPassword(user.getId(), changeMyPasswordDTO);
			
			model.addAttribute("changeMyPasswordInformation", this.messageSource.getMessage("mypassword.success", 
																null, 
																locale));
			
			return "ChangeMyPassword";
		}
		else {
			logger.debug("ChangeMyPassword is not valid");
			
			model.addAttribute("errors", errors);
			
			return "ChangeMyPassword";
		}
    }
}
