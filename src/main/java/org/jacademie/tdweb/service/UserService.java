package org.jacademie.tdweb.service;

import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.LoginPasswordDTO;

public interface UserService {

	boolean validateSignIn(LoginPasswordDTO loginPasswordDTO);

	Integer determineNbTotalUsers();

	Integer determineRankingForUser(Integer id);

	User findUserByLogin(String login);

	User findUserById(Integer id);
}
