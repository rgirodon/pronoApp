package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.controller.helper.HelloHelper;
import org.jacademie.tdweb.domain.User;
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
@SessionAttributes(value={"registerDTO"})
@RequestMapping("/Register")
public class RegisterController {

	private static Logger logger = Logger.getLogger(RegisterController.class);
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(method = RequestMethod.GET)
	public String displayRegisterHandler(ModelMap model) {
		
		logger.debug("In displayRegisterHandler");
		
		RegisterDTO registerDTO = new RegisterDTO();
				
		model.addAttribute("registerDTO", registerDTO);
		
		return "Register";
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String validateRegisterHandler(@ModelAttribute RegisterDTO registerDTO, ModelMap model) {
		
		logger.debug("In validateRegisterHandler");
		
		logger.debug("login : " + registerDTO.getLogin());
		
		logger.debug("password : " + registerDTO.getPassword());
		
		logger.debug("re-enter password : " + registerDTO.getReEnterPassword());
		
		Collection<String> errors = this.userService.validateRegister(registerDTO);
		
		if (errors.isEmpty()) {
		
			logger.debug("Register is valid");
			
			this.userService.register(registerDTO);
			
			model.addAttribute("registerInformation", "Successfully registered, now sign in ;)");
			
			return "redirect:SignIn.do";
		}
		else {
			logger.debug("Register is not valid");
			
			model.addAttribute("errors", errors);
			
			return "Register";
		}
    }
}
