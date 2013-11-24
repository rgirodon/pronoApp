package org.jacademie.tdweb.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	private static Logger logger = Logger.getLogger(HelloController.class);
	
	@RequestMapping("/Hello")
	public String helloHandler(ModelMap model) {
		
		logger.debug("In helloHandler");
		
		model.addAttribute("message", "Hello World !");
		
		return "Hello";
    }
}
