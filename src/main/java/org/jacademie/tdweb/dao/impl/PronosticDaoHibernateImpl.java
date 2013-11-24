package org.jacademie.tdweb.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.tdweb.dao.PronosticDao;
import org.jacademie.tdweb.domain.Pronostic;
import org.jacademie.tdweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PronosticDaoHibernateImpl implements PronosticDao {

	private static Logger logger = Logger.getLogger(PronosticDaoHibernateImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
	
	@Override
	public Pronostic retrievePronosticForGameAndUser(Integer gameId,
			Integer userId) {
		
		logger.debug("In retrievePronosticForGameAndUser with param : " + gameId + ", " + userId);
    	
		Pronostic result = null;
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("pronosticByGameAndUser");
    	query.setInteger("gameId", gameId);
    	query.setInteger("userId", userId);
    	
    	List<Pronostic> pronostics = query.list();
    	
    	if (!pronostics.isEmpty()) {
    		
    		result = pronostics.iterator().next();
    		
    		logger.debug("Found a result : " + result);
    	}
    	else {
    		logger.debug("Found no result");
    	}
    	
    	return result;
	}

}
