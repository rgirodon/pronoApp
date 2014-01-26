package org.jacademie.tdweb.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.jacademie.tdweb.domain.League;
import org.jacademie.tdweb.domain.LeagueParticipation;
import org.jacademie.tdweb.domain.User;
import org.jacademie.tdweb.dto.NotificationDTO;
import org.jacademie.tdweb.sendgrid.MultipleBccSendGrid;
import org.jacademie.tdweb.service.LeagueService;
import org.jacademie.tdweb.service.MailService;
import org.jacademie.tdweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class MailServiceImpl implements MailService {

	private String username;
	
	private String password;

	private String from;
	
	private String webSiteUrl;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Override
	public void sendInvitationMail(String friend, String invitor, String league) {
		
		Locale locale = LocaleContextHolder.getLocale();
		
		MultipleBccSendGrid sendGrid = new MultipleBccSendGrid(this.username, this.password);
		
		sendGrid.addTo(friend);
		sendGrid.setFrom(this.from);
		
		String subject = this.messageSource.getMessage("invitation.subject", 
														null, 
														locale);
		
		sendGrid.setSubject(subject);
		
		StringBuilder text = new StringBuilder();
		text.append("<h1>").append(this.messageSource.getMessage("invitation.hey", null, locale)).append("</h1>");
		text.append("<h2>").append(invitor).append(" ").append(this.messageSource.getMessage("invitation.hasInvitedYou", null, locale)).append(" ").append(league).append(".</h2>");
		text.append("<h2> ").append(this.messageSource.getMessage("invitation.click", null, locale)).append(" <a href=\"").append(this.webSiteUrl).append("\">").append(this.messageSource.getMessage("invitation.here", null, locale)).append("</a> ");
		text.append(this.messageSource.getMessage("invitation.register", null, locale)).append("</h2>");
		text.append("<h3>").append(this.messageSource.getMessage("mail.footer", null, locale)).append("</h3>");
		sendGrid.setHtml(text.toString());

		sendGrid.send();
	}
	
	@Override
	public void sendLeagueUsersNotification(Integer leagueId, NotificationDTO notificationDTO) {
		
		Locale locale = LocaleContextHolder.getLocale();
		
		League league = this.leagueService.findLeagueById(leagueId);
		
		Collection<String> recipients = new HashSet<>();
		
		for (LeagueParticipation leagueParticipation : league.getLeagueParticipations()) {
			
			recipients.add(leagueParticipation.getUser().getLogin());
		}
		
		
		MultipleBccSendGrid sendGrid = new MultipleBccSendGrid(this.username, this.password);
		
		sendGrid.addTo(this.from);
		
		for (String recipient : recipients) {
		
			sendGrid.addBcc(recipient);
		}
		
		sendGrid.setFrom(this.from);
		
		StringBuilder subject = new StringBuilder();
		subject.append(this.messageSource.getMessage("notification.subject", null, locale)).append(" ").append(league.getName()).append(" : ").append(notificationDTO.getSubject());
		sendGrid.setSubject(subject.toString());
		
		StringBuilder text = new StringBuilder();
		
		String formattedText = StringUtils.replace(notificationDTO.getText(), "\n", "<br/>");	
		text.append(formattedText);
		
		text.append("<h2> ").append(this.messageSource.getMessage("notification.click", null, locale)).append(" <a href=\"").append(this.webSiteUrl).append("\">").append(this.messageSource.getMessage("notification.here", null, locale)).append("</a> ");
		text.append(this.messageSource.getMessage("notification.enter", null, locale)).append("</h2>");
		
		text.append("<h3>").append(this.messageSource.getMessage("mail.footer", null, locale)).append("</h3>");
		
		sendGrid.setHtml(text.toString());

		sendGrid.send();
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getWebSiteUrl() {
		return webSiteUrl;
	}


	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	
}
