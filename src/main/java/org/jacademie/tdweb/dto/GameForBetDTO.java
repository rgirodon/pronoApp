package org.jacademie.tdweb.dto;

import java.io.Serializable;

import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.Pronostic;

public class GameForBetDTO implements Serializable {

	private Game game;
	
	private Pronostic pronostic;

	public GameForBetDTO() {
		super();
	}

	public String getLogin() {
		
		return this.getPronostic().getUser().getLogin();
	}
	
	public String getDisplayName() {
		
		return this.getPronostic().getUser().getDisplayName();
	}
	
	public String getPronosticScore() {
		
		return this.getPronostic().getScore();
	}
	
	public Integer getIdGame() {
		
		return this.getGame().getId();
	}
	
	public void setScoreTeam1(Integer scoreTeam1) {
		
		this.getPronostic().setScoreTeam1(scoreTeam1);
	}
	
	public void setScoreTeam2(Integer scoreTeam2) {
		
		this.getPronostic().setScoreTeam2(scoreTeam2);
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
