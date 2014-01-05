package org.jacademie.tdweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.UserDao;
import org.jacademie.tdweb.domain.Game;
import org.jacademie.tdweb.domain.Invitation;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.LeagueParticipation;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.ChangeMyDisplayNameDTO;
import org.jacademie.tdweb.dto.ChangeMyPasswordDTO;
import org.jacademie.tdweb.dto.LoginPasswordDTO;
import org.jacademie.tdweb.dto.RegisterDTO;
import org.jacademie.tdweb.service.GameService;
import org.jacademie.tdweb.service.LeagueService;
import org.jacademie.tdweb.service.MailService;
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
	private GameService gameService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private MailService mailService;
		
	@Autowired
	private UserDao userDao;
	
	@Override
	public Collection<Invitation> retrieveInvitationsForUser(Integer id) {
		
		logger.debug("In retrieveInvitationsForUser");
		
		User user = this.userDao.findUserById(id);
		
		String email = user.getLogin();
		
		return this.userDao.findInvitationsForEmail(email);
	}
	
	@Override
	public boolean validateSignIn(LoginPasswordDTO loginPasswordDTO) {
		
		logger.debug("In validateSignIn");
		
		logger.debug("login : " + loginPasswordDTO.getLogin());
		
		logger.debug("password : " + loginPasswordDTO.getEncryptedPassword());
		
		User user = this.userDao.findUserByLogin(loginPasswordDTO.getLogin());
		
		if (user == null) {
		
			logger.debug("Login / Password is not valid (Login not found)");
			
			return false;
		}
		else {
			if (StringUtils.equals(loginPasswordDTO.getEncryptedPassword(), user.getPassword())) {
				
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
	public Integer determineNbTotalUsersForLeague(Integer leagueId) {
		
		return this.userDao.findAllUsersForLeague(leagueId).size();
	}

	@Override
	public Integer determineRankingForUserForLeague(Integer userId, Integer leagueId) {
		
		Integer result = 1;
		
		User user = this.findUserById(userId);
		
		for (User otherUser : this.userDao.findAllUsersForLeague(leagueId)) {
			
			if (!otherUser.equals(user)) {
				
				if (otherUser.getPointsForLeague(leagueId) > user.getPointsForLeague(leagueId)) {
					
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
	public Collection<User> retrieveRankingUsersForLeague(Integer leagueId) {
		
		return this.userDao.retrieveRankingUsersForLeague(leagueId);
	}
	
	@Override
	public Collection<User> retrieveUsersForLeague(Integer leagueId) {
		
		return this.userDao.findAllUsersForLeague(leagueId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void register(RegisterDTO registerDTO) {
		
		User user = new User();
		user.setAdmin(Boolean.FALSE);
		user.setLogin(registerDTO.getLogin());
		user.setPassword(registerDTO.getEncryptedPassword());
		user.setDisplayName(registerDTO.getDisplayName());
		
		this.userDao.createUser(user);
	}

	@Override
	public boolean checkInvitationIsValid(String friend) {
		
		// check email is a mail
		if (!EmailValidator.getInstance().isValid(friend)) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void inviteFriend(String friend, Integer userId, Integer leagueId) {
		
		User user = this.findUserById(userId);
		
		League league = this.leagueService.findLeagueById(leagueId);
		
		Invitation invitation = new Invitation();
		
		invitation.setEmail(friend);
		invitation.setLeague(league);
		
		this.userDao.createInvitation(invitation);
		
		this.mailService.sendInvitationMail(friend, user.getDisplayName(), league.getName());
	}
	
	@Override
	public boolean checkUserIsInvolvedInLeague(String friend, Integer leagueId) {
		
		boolean result = false;
		
		User user = this.findUserByLogin(friend);
		
		if (user != null) {
			
			if (user.isInvolvedInLeague(leagueId)) {
				
				result = true;
			}
		}
		
		return result;
	}
	
	@Override
	public boolean checkUserIsInvitedToLeague(String friend, Integer leagueId) {
		
		boolean result = false;
		
		Collection<Invitation> invitations = this.userDao.findInvitationsForEmail(friend);
		
		for (Invitation invitation : invitations) {
			
			if (invitation.getLeague().getId().equals(leagueId)) {
				
				result = true;
				break;
			}
		}
		
		return result;
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
		
		// check displayName is not empty
		if (StringUtils.isEmpty(registerDTO.getDisplayName())) {
			
			errors.add("Display name is mandatory");
		}
		
		// check displayName is unique
		User userWithSameDisplayName = this.findUserByDisplayName(registerDTO.getDisplayName());
		if (userWithSameDisplayName != null) {
			
			errors.add("Display name is already existing");
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

	private User findUserByDisplayName(String displayName) {
		
		return this.userDao.findUserByDisplayName(displayName);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void changeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO) {
		
		User user = this.findUserById(userId);
		
		user.setPassword(changeMyPasswordDTO.getEncryptedNewPassword());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void changeMyDisplayName(Integer userId,
			ChangeMyDisplayNameDTO changeMyDisplayNameDTO) {
		
		User user = this.findUserById(userId);
		
		user.setDisplayName(changeMyDisplayNameDTO.getDisplayNameInput());
	}
	
	@Override	
	public Collection<String> validateChangeMyPassword(Integer userId,
			ChangeMyPasswordDTO changeMyPasswordDTO) {
		
		Collection<String> errors = new ArrayList<>();
		
		User user = this.findUserById(userId);
		
		// check oldPassword and user password are equals
		if (!StringUtils.equals(changeMyPasswordDTO.getEncryptedOldPassword(), user.getPassword())) {
			
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
	
	@Override	
	public Collection<String> validateChangeMyDisplayName(Integer userId,
			ChangeMyDisplayNameDTO changeMyDisplayNameDTO) {
		
		Collection<String> errors = new ArrayList<>();
		
		// check displayName is not empty
		if (StringUtils.isEmpty(changeMyDisplayNameDTO.getDisplayNameInput())) {
			
			errors.add("Display name is mandatory");
		}
		
		User user = this.findUserByDisplayName(changeMyDisplayNameDTO.getDisplayNameInput());
		
		if ((user != null)
				&& (!user.getId().equals(userId))) {
			
			errors.add("Display name is already existing");
		}
		
		return errors;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void reComputeRankingForLeague(Integer leagueId) {
		
		Collection<Integer> leagueIdsToReset = new HashSet<>();
		leagueIdsToReset.add(leagueId);
		
		Collection<League> leaguesInheritingFromLeague = this.leagueService.retrieveLeaguesInheritingFromLeague(leagueId);
		
		for (League leagueInheritingFromLeague : leaguesInheritingFromLeague) {
			
			leagueIdsToReset.add(leagueInheritingFromLeague.getId());
		}
		
		for (Integer leagueIdToReset : leagueIdsToReset) {
			
			Collection<User> allUsers = this.retrieveUsersForLeague(leagueIdToReset);
			
			for (User user : allUsers) {
				
				user.resetPointsForLeague(leagueIdToReset);
			}
		}
			
		Collection<Game> pointsComputedGames = gameService.retrievePointsComputedGamesForLeague(leagueId);
		
		for (Game game : pointsComputedGames) {
			
			gameService.computePointsForGame(game.getId());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addToLeagueAdmins(Integer userId, Integer leagueId) {
		
		User user = this.userDao.findUserById(userId);
		
		LeagueParticipation leagueParticipation = user.getLeagueParticipation(leagueId);
		
		leagueParticipation.setAdmin(Boolean.TRUE);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void removeFromLeagueAdmins(Integer userId, Integer leagueId) {
		
		User user = this.userDao.findUserById(userId);
		
		LeagueParticipation leagueParticipation = user.getLeagueParticipation(leagueId);
		
		leagueParticipation.setAdmin(Boolean.FALSE);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void involveUserInLeague(Integer userId, Integer leagueId) {
		
		User user = this.userDao.findUserById(userId);
		
		League league = this.leagueService.findLeagueById(leagueId);
		
		LeagueParticipation leagueParticipation = new LeagueParticipation();
		leagueParticipation.setAdmin(Boolean.FALSE);		
		leagueParticipation.setNbComputedPronos(0);
		leagueParticipation.setNbCorrectResults(0);
		leagueParticipation.setNbExactScores(0);
		leagueParticipation.setPoints(0);
		
		
		league.addLeagueParticipation(leagueParticipation);
		user.addLeagueParticipation(leagueParticipation);		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void involveUserInLeagueAsAdmin(Integer userId, Integer leagueId) {
		
		User user = this.userDao.findUserById(userId);
		
		League league = this.leagueService.findLeagueById(leagueId);
		
		LeagueParticipation leagueParticipation = new LeagueParticipation();
		leagueParticipation.setAdmin(Boolean.TRUE);		
		leagueParticipation.setNbComputedPronos(0);
		leagueParticipation.setNbCorrectResults(0);
		leagueParticipation.setNbExactScores(0);
		leagueParticipation.setPoints(0);
		
		
		league.addLeagueParticipation(leagueParticipation);
		user.addLeagueParticipation(leagueParticipation);		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void declineInvitation(Integer invitationId) {
		
		this.userDao.deleteInvitation(invitationId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void acceptInvitation(Integer invitationId, Integer userId) {
		
		Invitation invitation = this.userDao.findInvitationById(invitationId);
		
		this.involveUserInLeague(userId, invitation.getLeague().getId());
		
		this.userDao.deleteInvitation(invitationId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void changeMyDefaultLeague(Integer userId, Integer defaultLeagueId) {
		
		User user = this.userDao.findUserById(userId);
		
		League defaultLeague = null;
		
		if (defaultLeagueId != -1) {
			
			defaultLeague = this.leagueService.findLeagueById(defaultLeagueId);
		}
		
		user.setDefaultLeague(defaultLeague);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteInvitationsForLeague(Integer leagueId) {
		
		Collection<Invitation> invitations = this.userDao.retrieveInvitationsForLeague(leagueId);
		
		for (Invitation invitation : invitations) {
			
			this.userDao.deleteInvitation(invitation.getId());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void resetDefaultLeagueForLeague(Integer leagueId) {
		
		Collection<User> users = this.userDao.retrieveUsersWithDefaultLeague(leagueId);
		
		for (User user : users) {
			
			user.setDefaultLeague(null);
		}
	}
}
