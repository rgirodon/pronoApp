package org.jacademie.tdweb.controller;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.service.MarkDownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Help")
public class HelpController {

	private static Logger logger = Logger.getLogger(HelpController.class);
	
	@Autowired
	private MarkDownService markDownService;
		
	@RequestMapping(method = RequestMethod.GET)
	public String displayHelpHandler(ModelMap model) {
		
		logger.debug("In displayHelpHandler");
		
		String helpText = markDownService.retrieveHelpContent();
		
		model.addAttribute("helpText", helpText);
		
		return "Help";
    }
}
