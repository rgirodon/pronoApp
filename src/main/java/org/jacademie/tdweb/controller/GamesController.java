package org.jacademie.tdweb.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/Games")
@SessionAttributes(value={"gameEdited","gameBeingCreated"})
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
	
	@RequestMapping(value="New", method = RequestMethod.GET)
	public String newHandler(ModelMap model) {
		
		logger.debug("Creating new game");
		
		Game gameBeingCreated = new Game();
		
		gameBeingCreated.setClosed(Boolean.TRUE);
		gameBeingCreated.setPointsComputed(Boolean.FALSE);
		
		model.addAttribute("gameBeingCreated", gameBeingCreated);
		
		return "CreateGame";
	}
	
	@RequestMapping(value="Finish", method = RequestMethod.POST)
	public String finishHandler(@ModelAttribute(value = "gameBeingCreated") Game gameBeingCreated,
								@RequestParam String strDate,
								@RequestParam String team1,
								@RequestParam String team2,
							 	ModelMap model) {
		
		logger.debug("Creating game");
		
		Collection<String> errors = new ArrayList<>();
		
		logger.debug("Date : " + strDate);
		if (StringUtils.isEmpty(strDate)) {
			
			errors.add("Date is mandatory");
		}
		else {
			String[] patterns = {DateFormatUtils.ISO_DATE_FORMAT.getPattern()};
			Date date = null;
			try {
				date = DateUtils.parseDate(strDate, patterns);
			}
			catch(ParseException pe) {
				errors.add("Date is invalid");
				date = null;
			}
			gameBeingCreated.setDate(date);
		}
		
		logger.debug("Team1 : " + team1);
		if (StringUtils.isEmpty(team1)) {
			
			errors.add("Team 1 is mandatory");
		}
		gameBeingCreated.setTeam1(team1);
		
		logger.debug("Team2 : " + team2);
		if (StringUtils.isEmpty(team2)) {
			
			errors.add("Team 2 is mandatory");
		}
		gameBeingCreated.setTeam2(team2);
		
		if (!errors.isEmpty()) {
			
			model.addAttribute("errors", errors);
			
			return "CreateGame";
		}
		else {
			gameService.createGame(gameBeingCreated);
			
			model.addAttribute("actionMessage", "Game successfully created"); 
			
			return this.listHandler(model);
		}
	}
	
	@RequestMapping(value="Edit", method = RequestMethod.GET)
	public String editHandler(@RequestParam Integer id, ModelMap model) {
		
		logger.debug("Editing game " + id);
		
		Game gameEdited = gameService.retrieveGameById(id);
		
		model.addAttribute("gameEdited", gameEdited);
		
		return "EditGame";
	}
	
	@RequestMapping(value="Save", method = RequestMethod.POST)
	public String saveHandler(@ModelAttribute(value = "gameEdited") Game gameEdited,
								@RequestParam String strDate,
								@RequestParam String team1,
								@RequestParam String team2,
								@RequestParam String strScoreTeam1,
								@RequestParam String strScoreTeam2,
							 	ModelMap model) {
		
		logger.debug("Saving game " + gameEdited.getId());
		
		Collection<String> errors = new ArrayList<>();
		
		logger.debug("Date : " + strDate);
		if (StringUtils.isEmpty(strDate)) {
			
			errors.add("Date is mandatory");
		}
		else {
			String[] patterns = {DateFormatUtils.ISO_DATE_FORMAT.getPattern()};
			Date date = null;
			try {
				date = DateUtils.parseDate(strDate, patterns);
			}
			catch(ParseException pe) {
				errors.add("Date is invalid");
				date = null;
			}
			gameEdited.setDate(date);
		}
		
		logger.debug("Team1 : " + team1);
		if (StringUtils.isEmpty(team1)) {
			
			errors.add("Team 1 is mandatory");
		}
		gameEdited.setTeam1(team1);
		
		logger.debug("Team2 : " + team2);
		if (StringUtils.isEmpty(team2)) {
			
			errors.add("Team 2 is mandatory");
		}
		gameEdited.setTeam2(team2);
		
		logger.debug("Score Team1 : " + strScoreTeam1);
		if (StringUtils.isEmpty(strScoreTeam1)) {
			gameEdited.setScoreTeam1(null);
		}
		else {
			if (NumberUtils.isDigits(strScoreTeam1)) {
				gameEdited.setScoreTeam1(Integer.parseInt(strScoreTeam1));
			}
			else {
				errors.add("Score Team 1 is invalid");
				gameEdited.setScoreTeam1(null);
			}
		}
		
		logger.debug("Score Team2 : " + strScoreTeam2);
		if (StringUtils.isEmpty(strScoreTeam2)) {
			gameEdited.setScoreTeam2(null);
		}
		else {
			if (NumberUtils.isDigits(strScoreTeam2)) {
				gameEdited.setScoreTeam2(Integer.parseInt(strScoreTeam2));
			}
			else {
				errors.add("Score Team 2 is invalid");
				gameEdited.setScoreTeam2(null);
			}
		}
		
		if (!errors.isEmpty()) {
			
			model.addAttribute("errors", errors);
			
			return "EditGame";
		}
		else {
			gameService.updateGame(gameEdited);
			
			model.addAttribute("actionMessage", "Game successfully saved"); 
			
			return this.listHandler(model);
		}
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
