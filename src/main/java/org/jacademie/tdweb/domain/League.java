package org.jacademie.tdweb.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="LEAGUE")
@NamedQueries({
	@NamedQuery(name="publicLeagues", query="from League where isPublic is true order by name"),
	@NamedQuery(name="leaguesByName", query="from League where upper(name) = :name order by name"),
	@NamedQuery(name="leaguesByInheritsGamesFrom", query="from League where inheritsGamesFromLeague.id = :inheritsGamesFromLeagueId order by name")
})
public class League implements Serializable {

	@Id
	@SequenceGenerator(name = "league_id_seq", sequenceName = "league_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "league_id_seq")
	private Integer id;
	
	private String name;
	
	private Boolean isPublic;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "inheritsGamesFromLeague_id")
	private League inheritsGamesFromLeague;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="league", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<LeagueParticipation> leagueParticipations;
	
	public League() {
		
		this.leagueParticipations = new HashSet<>();
	}
	
	public void addLeagueParticipation(LeagueParticipation leagueParticipation) {
		
		leagueParticipation.setLeague(this);
		this.leagueParticipations.add(leagueParticipation);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		League other = (League) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public League getInheritsGamesFromLeague() {
		return inheritsGamesFromLeague;
	}

	public void setInheritsGamesFromLeague(League inheritsGamesFromLeague) {
		this.inheritsGamesFromLeague = inheritsGamesFromLeague;
	}

	public Set<LeagueParticipation> getLeagueParticipations() {
		return leagueParticipations;
	}

	public void setLeagueParticipations(
			Set<LeagueParticipation> leagueParticipations) {
		this.leagueParticipations = leagueParticipations;
	}

	
}
