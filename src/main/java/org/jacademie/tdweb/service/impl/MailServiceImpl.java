package org.jacademie.tdweb.service.impl;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashSet;

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
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public class MailServiceImpl implements MailService {

	private String username;
	
	private String password;

	private String from;
	
	private String webSiteUrl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Override
	public void sendInvitationMail(String friend, String invitor, String league) {
		
		MultipleBccSendGrid sendGrid = new MultipleBccSendGrid(this.username, this.password);
		
		sendGrid.addTo(friend);
		sendGrid.setFrom(this.from);
		sendGrid.setSubject("PronoClub - You're invited to join a league !");
		
		StringBuilder text = new StringBuilder();
		text.append("<h1>Hey !</h1>");
		text.append("<h2>").append(invitor).append(" has invited you to join league ").append(league).append(".</h2>");
		text.append("<h2>Click <a href=\"").append(this.webSiteUrl).append("\">here</a> to register and enter the Prono Club (don't forget to read the rules before you sign in...).</h2>");
		text.append("<h3>-Mail sent by PronoClub team-</h3>");
		sendGrid.setHtml(text.toString());

		sendGrid.send();
	}
	
	@Override
	public void sendLeagueUsersNotification(Integer leagueId, NotificationDTO notificationDTO) {
		
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
		subject.append("PronoClub - Notification for league ").append(league.getName()).append(" : ").append(notificationDTO.getSubject());
		sendGrid.setSubject(subject.toString());
		
		StringBuilder text = new StringBuilder();
		
		String formattedText = StringUtils.replace(notificationDTO.getText(), "\n", "<br/>");	
		text.append(formattedText);
		
		text.append("<h2>Click <a href=\"").append(this.webSiteUrl).append("\">here</a> to enter the Prono Club.</h2>");
		
		text.append("<h3>-Mail sent by PronoClub team-</h3>");
		
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
