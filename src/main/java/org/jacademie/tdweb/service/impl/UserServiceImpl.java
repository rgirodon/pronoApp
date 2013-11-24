package org.jacademie.tdweb.service.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.UserDao;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Override
	public boolean validateSignIn(LoginPasswordDTO loginPasswordDTO) {
		
		logger.debug("In validateSignIn");
		
		logger.debug("login : " + loginPasswordDTO.getLogin());
		
		logger.debug("password : " + loginPasswordDTO.getPassword());
		
		User user = this.userDao.findUserByLogin(loginPasswordDTO.getLogin());
		
		if (user == null) {
		
			logger.debug("Login / Password is not valid (Login not found)");
			
			return false;
		}
		else {
			if (StringUtils.equals(loginPasswordDTO.getPassword(), user.getPassword())) {
				
				logger.debug("Login / Password is valid");
				
				return true;
			}
			else {
				logger.debug("Login / Password is not valid (Invalid password)");
				
				return false;
			}
		}
	}

	@Override
	public Integer determineNbTotalUsers() {
		
		return this.userDao.findAllUsers().size();
	}

	@Override
	public Integer determineRankingForUser(Integer id) {
		
		Integer result = 1;
		
		User user = this.findUserById(id);
		
		for (User otherUser : this.userDao.findAllUsers()) {
			
			if (!otherUser.equals(user)) {
				
				if (otherUser.getPoints() > user.getPoints()) {
					
					result++;
				}
			}
		}
		
		return result;
	}

	@Override
	public User findUserById(Integer id) {

		return this.userDao.findUserById(id);
	}

	@Override
	public User findUserByLogin(String login) {
		
		return this.userDao.findUserByLogin(login);
	}

}
