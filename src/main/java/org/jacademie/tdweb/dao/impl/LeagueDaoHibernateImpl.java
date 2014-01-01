package org.jacademie.tdweb.dao.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.tdweb.dao.LeagueDao;
import org.jacademie.tdweb.domain.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LeagueDaoHibernateImpl implements LeagueDao {

	private static Logger logger = Logger.getLogger(LeagueDaoHibernateImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

	@Override
	public League findLeagueById(Integer leagueId) {
		
		Session session = this.getCurrentSession();
		
		return (League)session.get(League.class, leagueId);
	}

	@Override
	public Collection<League> retrievePublicLeagues() {
		
		Session session = this.getCurrentSession();
		
		Query query = session.getNamedQuery("publicLeagues");
    	
    	return query.list();
	}
	
	@Override
	public Collection<League> retrieveLeaguesInheritingFromLeague(Integer leagueId) {
		
		Session session = this.getCurrentSession();
		
		Query query = session.getNamedQuery("leaguesByInheritsGamesFrom");
		query.setInteger("inheritsGamesFromLeagueId", leagueId);
    	
    	return query.list();
	}

	@Override
	public void createLeague(League league) {
		
		Session session = this.getCurrentSession();
		
		session.save(league);
	}
	
	@Override
	public void saveLeague(League league) {
		
		Session session = this.getCurrentSession();
		
		session.merge(league);
	}
	
	@Override
	public Collection<League> findLeaguesByName(String name) {
		
		Session session = this.getCurrentSession();
		
		Query query = session.getNamedQuery("leaguesByName");
		query.setString("name", name.toUpperCase());
    	
    	return query.list();
	}
}
