package org.jacademie.tdweb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="GAME")
@NamedQueries({
	@NamedQuery(name="openedGames", query="from Game where closed = false")
})
public class Game {

	@Id
	private Integer id;
	
	private String team1;
	
	private String team2;
	
	private Integer scoreTeam1;
	
	private Integer scoreTeam2;
	
	private Date date;
	
	private Boolean closed;

	public Game() {
		super();
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", team1=" + team1 + ", team2=" + team2
				+ ", date=" + date + ", closed=" + closed + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((team1 == null) ? 0 : team1.hashCode());
		result = prime * result + ((team2 == null) ? 0 : team2.hashCode());
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
		Game other = (Game) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (team1 == null) {
			if (other.team1 != null)
				return false;
		} else if (!team1.equals(other.team1))
			return false;
		if (team2 == null) {
			if (other.team2 != null)
				return false;
		} else if (!team2.equals(other.team2))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public Integer getScoreTeam1() {
		return scoreTeam1;
	}

	public void setScoreTeam1(Integer scoreTeam1) {
		this.scoreTeam1 = scoreTeam1;
	}

	public Integer getScoreTeam2() {
		return scoreTeam2;
	}

	public void setScoreTeam2(Integer scoreTeam2) {
		this.scoreTeam2 = scoreTeam2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}
	
	
}
