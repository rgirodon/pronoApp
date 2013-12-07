package org.jacademie.tdweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.UserDao;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

	@Override
	public Collection<User> retrieveRankingUsers() {
		
		return this.userDao.retrieveRankingUsers();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void register(RegisterDTO registerDTO) {
		
		User user = new User();
		user.setAdmin(Boolean.FALSE);
		user.setLogin(registerDTO.getLogin());
		user.setPassword(registerDTO.getPassword());
		user.setPoints(0);
		
		this.userDao.createUser(user);
	}

	@Override
	public Collection<String> validateRegister(RegisterDTO registerDTO) {
		
		Collection<String> errors = new ArrayList<>();
		
		// check login is not empty
		if (StringUtils.isEmpty(registerDTO.getLogin())) {
			
			errors.add("Login is mandatory");
		}
		
		// check login is unique
		User userWithSameLogin = this.findUserByLogin(registerDTO.getLogin());
		if (userWithSameLogin != null) {
			
			errors.add("Login is already existing");
		}
		
		// check email is a mail
		if (!EmailValidator.getInstance().isValid(registerDTO.getLogin())) {
			
			errors.add("Login must be a valid email");
		}
		
		// check password is not empty
		if (StringUtils.isEmpty(registerDTO.getPassword())) {
			
			errors.add("Password is mandatory");
		}
		
		// check password and reEnterPassword are equals
		if (!StringUtils.equals(registerDTO.getPassword(), registerDTO.getReEnterPassword())) {
			
			errors.add("Passwords are not the same");
		}
		
		return errors;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void changeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO) {
		
		User user = this.findUserById(userId);
		
		user.setPassword(changeMyPasswordDTO.getNewPassword());
	}

	@Override	
	public Collection<String> validateChangeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO) {
		
		Collection<String> errors = new ArrayList<>();
		
		User user = this.findUserById(userId);
		
		// check oldPassword and user password are equals
		if (!StringUtils.equals(changeMyPasswordDTO.getOldPassword(), user.getPassword())) {
			
			errors.add("Old Password is not correct");
		}
		
		// check new password is not empty
		if (StringUtils.isEmpty(changeMyPasswordDTO.getNewPassword())) {
			
			errors.add("New Password is mandatory");
		}
		
		// check newPassword and reEnterNewPassword are equals
		if (!StringUtils.equals(changeMyPasswordDTO.getNewPassword(), changeMyPasswordDTO.getReEnterNewPassword())) {
			
			errors.add("New Passwords are not the same");
		}
		
		return errors;
	}
}
