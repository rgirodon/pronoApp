package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Games")
public class GamesController {

	private static Logger logger = Logger.getLogger(GamesController.class);
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value="List", method = RequestMethod.GET)
	public String listHandler(ModelMap model) {
		
		logger.debug("List games.");
		
		Collection<Game> games = gameService.retrieveGames();
		
		model.addAttribute("games", games);
		
		return "Games";
	}
}
