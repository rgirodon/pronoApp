package org.jacademie.tdweb.dao.impl;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.tdweb.dao.UserDao;
import org.jacademie.tdweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoHibernateImpl implements UserDao {

	private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);
	
	@Autowired
    private SessionFactory sessionFactory;
 
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public User findUserByLogin(String login) {
    	
    	logger.debug("In findUserByLogin with param : " + login);
    	
    	User result = null;
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("userByLogin");
    	query.setString("login", login);
    	
    	List<User> users = query.list();
    	
    	if (!users.isEmpty()) {
    		
    		result = users.iterator().next();
    		
    		logger.debug("Found a result : " + result);
    	}
    	else {
    		logger.debug("Found no result");
    	}
    	
    	return result;
    }

	@Override
	public Collection<User> findAllUsers() {
		
		Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("allUsers");
    	
    	return query.list();
	}

	@Override
	public User findUserById(Integer id) {
		
		Session session = this.getCurrentSession();
		
		return (User)session.get(User.class, id);
	}
}
