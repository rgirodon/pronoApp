package org.jacademie.tdweb.dto;

import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.Pronostic;

public class GameForBetDTO {

	private Game game;
	
	private Pronostic pronostic;

	public GameForBetDTO() {
		super();
	}

	public String getTeam1() {
		
		return this.getGame().getTeam1();
	}
	
	public String getTeam2() {
		
		return this.getGame().getTeam2();
	}
	
	public Integer getScoreTeam1() {
		
		return this.getPronostic().getScoreTeam1();
	}
	
	public Integer getScoreTeam2() {
		
		return this.getPronostic().getScoreTeam2();
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Pronostic getPronostic() {
		return pronostic;
	}

	public void setPronostic(Pronostic pronostic) {
		this.pronostic = pronostic;
	}
	
	
}
