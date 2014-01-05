package org.jacademie.tdweb.dao;

import java.util.Collection;

import org.jacademie.tdweb.domain.Invitation;
import org.jacademie.tdweb.domain.User;

public interface UserDao {

	User findUserByLogin(String login);

	Collection<User> findAllUsers();
	
	Collection<User> findAllUsersForLeague(Integer leagueId);

	User findUserById(Integer id);

	Collection<User> retrieveRankingUsersForLeague(Integer leagueId);

	void createUser(User user);

	Collection<Invitation> findInvitationsForEmail(String email);

	User findUserByDisplayName(String displayName);

	void createInvitation(Invitation invitation);

	Invitation findInvitationById(Integer invitationId);

	void deleteInvitation(Integer invitationId);

	Collection<Invitation> retrieveInvitationsForLeague(Integer leagueId);

	Collection<User> retrieveUsersWithDefaultLeague(Integer leagueId);
}
