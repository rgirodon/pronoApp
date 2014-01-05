package org.jacademie.tdweb.service;

import java.util.Collection;
import java.util.HashMap;

import org.jacademie.tdweb.domain.Invitation;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;

public interface UserService {

	boolean validateSignIn(LoginPasswordDTO loginPasswordDTO);

	Integer determineNbTotalUsersForLeague(Integer leagueId);

	Integer determineRankingForUserForLeague(Integer userId, Integer leagueId);

	User findUserByLogin(String login);

	User findUserById(Integer id);

	Collection<User> retrieveRankingUsersForLeague(Integer leagueId);

	void register(RegisterDTO registerDTO);

	Collection<String> validateRegister(RegisterDTO registerDTO);

	void changeMyPassword(Integer userId, ChangeMyPasswordDTO changeMyPasswordDTO);

	Collection<String> validateChangeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO);

	Collection<User> retrieveUsersForLeague(Integer leagueId);

	 void reComputeRankingForLeague(Integer leagueId);

	Collection<Invitation> retrieveInvitationsForUser(Integer id);

	void addToLeagueAdmins(Integer userId, Integer leagueId);

	void removeFromLeagueAdmins(Integer userId, Integer leagueId);

	void involveUserInLeague(Integer userId, Integer leagueId);

	void involveUserInLeagueAsAdmin(Integer userId, Integer leagueId);

	boolean checkInvitationIsValid(String friend);

	boolean checkUserIsInvolvedInLeague(String friend, Integer leagueId);

	void inviteFriend(String friend, Integer userId, Integer leagueId);

	void declineInvitation(Integer invitationId);

	void acceptInvitation(Integer invitationId, Integer userId);

	void changeMyDefaultLeague(Integer userId, Integer defaultLeagueId);

	boolean checkUserIsInvitedToLeague(String friend, Integer leagueId);

	void deleteInvitationsForLeague(Integer leagueId);

	void resetDefaultLeagueForLeague(Integer leagueId);
}
