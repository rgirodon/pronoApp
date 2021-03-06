package org.jacademie.tdweb.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LEAGUE_PARTICIPATION")
public class LeagueParticipation implements Serializable {

	@Id
	@SequenceGenerator(name = "league_participation_id_seq", sequenceName = "league_participation_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "league_participation_id_seq")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "utilisateur_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "league_id")
	private League league;
	
	private Integer points;
	
	@Column(name="NB_CORRECT_RESULTS")
	private Integer nbCorrectResults;
	
	@Column(name="NB_EXACT_SCORES")
	private Integer nbExactScores;
	
	@Column(name="NB_COMPUTED_PRONOS")
	private Integer nbComputedPronos;
	
	private Boolean admin;
	
	public Integer getNbCorrectButInexactResults() {
		
		return this.getNbCorrectResults() - this.getNbExactScores();
	}
	
	public void resetPoints() {
		
		this.setPoints(0);
		this.setNbComputedPronos(0);
		this.setNbCorrectResults(0);
		this.setNbExactScores(0);
	}
	
	public void addPoints(Integer points) {
		
		this.points = this.points + points;
	}
	
	public void incrementNbComputedPronos() {
		
		this.nbComputedPronos++;
	}
	
	public void incrementNbCorrectResults() {

		this.nbCorrectResults++;
	}

	public void incrementNbExactScores() {
		
		this.nbExactScores++;
	}
	
	public Integer getLeagueId() {
		
		return this.getLeague().getId();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getNbCorrectResults() {
		return nbCorrectResults;
	}

	public void setNbCorrectResults(Integer nbCorrectResults) {
		this.nbCorrectResults = nbCorrectResults;
	}

	public Integer getNbExactScores() {
		return nbExactScores;
	}

	public void setNbExactScores(Integer nbExactScores) {
		this.nbExactScores = nbExactScores;
	}

	public Integer getNbComputedPronos() {
		return nbComputedPronos;
	}

	public void setNbComputedPronos(Integer nbComputedPronos) {
		this.nbComputedPronos = nbComputedPronos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((league == null) ? 0 : league.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeagueParticipation other = (LeagueParticipation) obj;
		if (league == null) {
			if (other.league != null)
				return false;
		} else if (!league.equals(other.league))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
