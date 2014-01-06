package org.jacademie.tdweb.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.GameForBetDTO;
import org.jacademie.tdweb.service.GameService;
import org.jacademie.tdweb.service.PronosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value={"user","league"})
@RequestMapping("/SeeOtherPronostics")
public class SeeOtherPronosticsController {

	private static Logger logger = Logger.getLogger(SeeOtherPronosticsController.class);
	
	@Autowired
	private GameService gameService;
	
	@Autowired
	private PronosticService pronosticService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String displaySeeOtherPronosticsHandler(@RequestParam Integer idGame, 
												   @ModelAttribute League league,		
												   @ModelAttribute User user, 
												   ModelMap model) {
		
		logger.debug("In displaySeeOtherPronosticsHandler with param : " + idGame);
		
		Game game = this.gameService.retrieveGameById(idGame); 
		
		model.addAttribute("game", game);
		
		Collection<GameForBetDTO> othersPronosticsForGame = this.pronosticService.retrieveOthersPronosticsForGameAndLeague(user.getId(), idGame, league.getId());
		
		model.addAttribute("othersPronosticsForGame", othersPronosticsForGame);
		
		return "SeeOtherPronostics";
	}
}
