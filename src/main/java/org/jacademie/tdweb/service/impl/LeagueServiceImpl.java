package org.jacademie.tdweb.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jacademie.tdweb.dao.LeagueDao;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.service.LeagueService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LeagueServiceImpl implements LeagueService {

	private static Logger logger = Logger.getLogger(LeagueServiceImpl.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueDao leagueDao;
	
	@Override
	public League findLeagueById(Integer leagueId) {
		
		logger.debug("In findLeagueById");
		
		return leagueDao.findLeagueById(leagueId);
	}
	
	@Override
	public Collection<League> retrievePublicLeagues() {
		
		logger.debug("In retrievePublicLeagues");
		
		Collection<League> publicLeagues = leagueDao.retrievePublicLeagues();
		
		return publicLeagues;
	}
	
	@Override
	public Collection<League> retrievePublicLeaguesAvailableForUser(Integer userId) {
		
		logger.debug("In retrievePublicLeaguesAvailableForUser");
		
		Collection<League> result = new HashSet<>();
		
		Collection<League> publicLeagues = leagueDao.retrievePublicLeagues();
		
		User user = this.userService.findUserById(userId);
		
		for (League publicLeague : publicLeagues) {
			
			if (!user.isInvolvedInLeague(publicLeague.getId())) {
				
				result.add(publicLeague);
			}
		}
		
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void createLeague(League league, Integer userId) {
		
		logger.debug("In createLeague");
		
		this.leagueDao.createLeague(league);
		
		this.userService.involveUserInLeagueAsAdmin(userId, league.getId());		
	}

	@Override
	public Collection<String> validateLeague(League league) {

		Locale locale = LocaleContextHolder.getLocale();
		
		Collection<String> errors = new ArrayList<>();
		
		// check name is not empty
		if (StringUtils.isEmpty(league.getName())) {
						
			errors.add(this.messageSource.getMessage("createleague.name.mandatory", 
					null, 
					locale));
		}
		
		// check name is not a doublon
		if (!StringUtils.isEmpty(league.getName())) {
			
			Collection<League> leaguesWithSameName = this.leagueDao.findLeaguesByName(league.getName());
			
			for (League leagueWithSameName : leaguesWithSameName) {
				
				if (!ObjectUtils.equals(leagueWithSameName.getId(), league.getId())) {
				
					errors.add(this.messageSource.getMessage("createleague.name.alreadyexists", 
							null, 
							locale));
				}
			}
		}
		
		return errors;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void closeLeague(Integer leagueId) {
		
		League league = this.findLeagueById(leagueId);
		
		league.setClosed(Boolean.TRUE);
		
		this.userService.deleteInvitationsForLeague(leagueId);
		
		this.userService.resetDefaultLeagueForLeague(leagueId);
		
		this.closeLeaguesInheritingFromLeague(leagueId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void closeLeaguesInheritingFromLeague(Integer leagueId) {
		
		Collection<League> leagues = this.retrieveLeaguesInheritingFromLeague(leagueId);
		
		for (League league : leagues) {
			
			this.closeLeague(league.getId());
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveLeague(League league) {
		
		logger.debug("In saveLeague");
		
		this.leagueDao.saveLeague(league);
	}

	@Override
	public Collection<League> retrieveLeaguesInheritingFromLeague(
			Integer leagueId) {
		
		logger.debug("In retrieveLeaguesInheritingFromLeague");
		
		return leagueDao.retrieveLeaguesInheritingFromLeague(leagueId);
	}

}
