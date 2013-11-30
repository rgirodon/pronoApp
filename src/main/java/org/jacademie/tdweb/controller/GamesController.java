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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="Close", method = RequestMethod.GET)
	public String closeHandler(@RequestParam Integer id, ModelMap model) {
		
		logger.debug("Closing game " + id);
		
		gameService.closeGame(id);
		
		model.addAttribute("actionMessage", "Game successfully closed.");
		
		return this.listHandler(model);
	}
	
	@RequestMapping(value="Open", method = RequestMethod.GET)
	public String openHandler(@RequestParam Integer id, ModelMap model) {
		
		logger.debug("Opening game " + id);
		
		gameService.openGame(id);
		
		model.addAttribute("actionMessage", "Game successfully opened.");
		
		return this.listHandler(model);
	}
	
	@RequestMapping(value="ComputePoints", method = RequestMethod.GET)
	public String computePointsHandler(@RequestParam Integer id, ModelMap model) {
		
		logger.debug("Computing points for game " + id);
		
		gameService.computePointsForGame(id);
		
		model.addAttribute("actionMessage", "Points successfully computed.");
		
		return this.listHandler(model);
	}
	
	@RequestMapping(value="Delete", method = RequestMethod.GET)
	public String deleteHandler(@RequestParam Integer id, ModelMap model) {
		
		if (gameService.canDeleteGame(id)) {
		
			logger.debug("Deleting game " + id);
			
			gameService.deleteGame(id);
			
			model.addAttribute("actionMessage", "Game successfully deleted.");
			
			return this.listHandler(model);
		}
		else {
			model.addAttribute("actionError", "Game can not be deleted because pronostics have already been made.");
			
			return this.listHandler(model);
		}
	}
}
