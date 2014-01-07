package org.jacademie.tdweb.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="UTILISATEUR")
@NamedQueries({
	@NamedQuery(name="userByLogin", query="from User where UPPER(login) = :login"),
	@NamedQuery(name="allUsers", query="from User order by login"),
	@NamedQuery(name="allUsersForLeague", query="Select u from User as u join u.leagueParticipations as lp where lp.league.id = :leagueId order by u.login"),
	@NamedQuery(name="rankingUsersForLeague", query="Select u from User as u join u.leagueParticipations as lp where lp.league.id = :leagueId order by lp.points desc, lp.nbCorrectResults desc"),
	@NamedQuery(name="userByDisplayName", query="from User where UPPER(displayName) = :displayName"),
	@NamedQuery(name="usersWithDefaultLeague", query="from User where defaultLeague.id = :leagueId")
})
public class User implements Serializable {

	@Id
	@SequenceGenerator(name = "utilisateur_id_seq", sequenceName = "utilisateur_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_id_seq")
	private Integer id;

	private String displayName;
	
	private String login;
	
	private String password;
	
	private Boolean admin;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Pronostic> pronostics;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<LeagueParticipation> leagueParticipations;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "default_league_id")
	private League defaultLeague;

	public User() {
		super();
		
		this.pronostics = new HashSet<>();
		this.leagueParticipations = new HashSet<>();
	}
	
	public void addLeagueParticipation(LeagueParticipation leagueParticipation) {
		
		leagueParticipation.setUser(this);
		
		this.leagueParticipations.add(leagueParticipation);
	}
	
	public Boolean isLeagueAdmin(Integer leagueId) {
		
		Boolean result = null;
		
		for (LeagueParticipation leagueParticipation : leagueParticipations) {
			
			if (leagueId.equals(leagueParticipation.getLeagueId())) {
				
				result = leagueParticipation.getAdmin();
				break;
			}
		}
		
		return result;
	} 
	
	public boolean isInvolvedInLeague(Integer leagueId) {
		
		return (this.getLeagueParticipation(leagueId) != null);
	}
	
	public Collection<League> getOpenLeaguesInvolvedIn() {
		
		Collection<League> result = new HashSet<>();
		
		for (LeagueParticipation leagueParticipation : leagueParticipations) {
			
			if (!leagueParticipation.getLeague().getClosed()) {
				
				result.add(leagueParticipation.getLeague());
			}
		}
		
		return result;
	}
	
	public LeagueParticipation getLeagueParticipation(Integer leagueId) {
		
		LeagueParticipation result = null;
		
		for (LeagueParticipation leagueParticipation : leagueParticipations) {
			
			if (leagueId.equals(leagueParticipation.getLeagueId())) {
				
				result = leagueParticipation;
				break;
			}
		}
		
		return result;
	}
	
	public Integer getNbInvolvedLeagues() {
		
		return this.getLeagueParticipations().size();
	}
	
	public Integer getNbCorrectButInexactResultsForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		return leagueParticipation.getNbCorrectButInexactResults();
	}
	
	public void resetPointsForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		leagueParticipation.resetPoints();
	}
	
	public void addPronostic(Pronostic pronostic) {
		
		pronostic.setUser(this);
		
		this.pronostics.add(pronostic);
	}
	
	public void addPointsForLeague(Integer leagueId, Integer points) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		leagueParticipation.addPoints(points);
	}
	
	public void incrementNbComputedPronosForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		leagueParticipation.incrementNbComputedPronos();
	}
	
	public void incrementNbCorrectResultsForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);

		leagueParticipation.incrementNbCorrectResults();
	}

	public void incrementNbExactScoresForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		leagueParticipation.incrementNbExactScores();
	}

	public int getPointsForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		return leagueParticipation.getPoints();
	}
	
	public int getNbComputedPronosForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		return leagueParticipation.getNbComputedPronos();
	}

	public int getNbCorrectResultsForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		return leagueParticipation.getNbCorrectResults();
	}
	
	public int getNbExactScoresForLeague(Integer leagueId) {
		
		LeagueParticipation leagueParticipation = this.getLeagueParticipation(leagueId);
		
		return leagueParticipation.getNbExactScores();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", displayName=" + displayName + ", login="
				+ login + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Set<Pronostic> getPronostics() {
		return pronostics;
	}

	public void setPronostics(Set<Pronostic> pronostics) {
		this.pronostics = pronostics;
	}

	public Set<LeagueParticipation> getLeagueParticipations() {
		return leagueParticipations;
	}

	public void setLeagueParticipations(Set<LeagueParticipation> leagueParticipations) {
		this.leagueParticipations = leagueParticipations;
	}

	public League getDefaultLeague() {
		return defaultLeague;
	}

	public void setDefaultLeague(League defaultLeague) {
		this.defaultLeague = defaultLeague;
	}

	

	

	
}
