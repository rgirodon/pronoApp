package org.jacademie.tdweb.service;

import java.util.Collection;

import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;

public interface UserService {

	boolean validateSignIn(LoginPasswordDTO loginPasswordDTO);

	Integer determineNbTotalUsers();

	Integer determineRankingForUser(Integer id);

	User findUserByLogin(String login);

	User findUserById(Integer id);

	Collection<User> retrieveRankingUsers();

	void register(RegisterDTO registerDTO);

	Collection<String> validateRegister(RegisterDTO registerDTO);

	void changeMyPassword(Integer userId, ChangeMyPasswordDTO changeMyPasswordDTO);

	Collection<String> validateChangeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO);

	Collection<User> retrieveUsers();

	void reComputeRanking();
}
