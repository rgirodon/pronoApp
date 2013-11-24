package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.User;

public interface UserDao {

	User findUserByLogin(String login);

	Collection<User> findAllUsers();

	User findUserById(Integer id);

	Collection<User> retrieveRankingUsers();	
}
