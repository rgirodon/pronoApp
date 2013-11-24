package org.jacademie.tdweb.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UTILISATEUR")
@NamedQueries({
	@NamedQuery(name="userByLogin", query="from User where login = :login"),
	@NamedQuery(name="allUsers", query="from User")
})
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String login;
	
	private String password;
	
	private Boolean admin;
	
	private Integer points;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
	private Set<Pronostic> pronostics;

	public User() {
		super();
		
		this.pronostics = new HashSet<>();
	}
	
	public void addPronostic(Pronostic pronostic) {
		
		pronostic.setUser(this);
		
		this.pronostics.add(pronostic);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", admin=" + admin + "]";
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

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Set<Pronostic> getPronostics() {
		return pronostics;
	}

	public void setPronostics(Set<Pronostic> pronostics) {
		this.pronostics = pronostics;
	}

	
	
	
}
