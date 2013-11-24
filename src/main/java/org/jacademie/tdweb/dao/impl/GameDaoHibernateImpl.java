package org.jacademie.tdweb.dao.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.tdweb.dao.GameDao;
import org.jacademie.tdweb.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDaoHibernateImpl implements GameDao {

	private static Logger logger = Logger.getLogger(GameDaoHibernateImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public Collection<Game> retrieveOpenedGames() {
		
		Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("openedGames");
    	
    	return query.list();
	}

}
