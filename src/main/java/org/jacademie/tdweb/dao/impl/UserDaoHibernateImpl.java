package org.jacademie.tdweb.dao.impl;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jacademie.tdweb.dao.UserDao;
import org.jacademie.tdweb.domain.Invitation;
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
    
    public Collection<Invitation> findInvitationsForEmail(String email) {
    	
    	logger.debug("In findInvitationsForEmail");
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("invitationsForEmail");
    	query.setString("email", email);
    	
    	return query.list();
    }
     
    public Collection<User> retrieveRankingUsersForLeague(Integer leagueId) {
    	
    	logger.debug("In retrieveRankingUsersForLeague");
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("rankingUsersForLeague");
    	query.setInteger("leagueId", leagueId);
    	
    	return query.list();
    }

    
    public User findUserByLogin(String login) {
    	
    	logger.debug("In findUserByLogin with param : " + login);
    	
    	User result = null;
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("userByLogin");
    	query.setString("login", login.toUpperCase());
    	
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
    public User findUserByDisplayName(String displayName) {
    	
    	logger.debug("In findUserByDisplayName with param : " + displayName);
    	
    	User result = null;
    	
    	Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("userByDisplayName");
    	query.setString("displayName", displayName.toUpperCase());
    	
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
	public Collection<User> findAllUsersForLeague(Integer leagueId) {
		
		Session session = this.getCurrentSession();
    	
    	Query query = session.getNamedQuery("allUsersForLeague");
    	query.setInteger("leagueId", leagueId);
    	
    	return query.list();
	}

	@Override
	public User findUserById(Integer id) {
		
		Session session = this.getCurrentSession();
		
		return (User)session.get(User.class, id);
	}

	@Override
	public void createUser(User user) {
		
		Session session = this.getCurrentSession();
		
		session.save(user);
	}

	@Override
	public void createInvitation(Invitation invitation) {

		Session session = this.getCurrentSession();
		
		session.save(invitation);
	}

	@Override
	public Invitation findInvitationById(Integer invitationId) {
		
		Session session = this.getCurrentSession();
		
		return (Invitation)session.get(Invitation.class, invitationId);
	}

	@Override
	public void deleteInvitation(Integer invitationId) {
		
		Session session = this.getCurrentSession();
				
		Invitation invitation = this.findInvitationById(invitationId);
		
		session.delete(invitation);
	}

	@Override
	public Collection<Invitation> retrieveInvitationsForLeague(Integer leagueId) {

		Session session = this.getCurrentSession();
		
		Query query = session.getNamedQuery("invitationsForLeague");
    	query.setInteger("leagueId", leagueId);
    	
    	return query.list();
	}

	@Override
	public Collection<User> retrieveUsersWithDefaultLeague(Integer leagueId) {
		
		Session session = this.getCurrentSession();
		
		Query query = session.getNamedQuery("usersWithDefaultLeague");
    	query.setInteger("leagueId", leagueId);
    	
    	return query.list();
	}
}
