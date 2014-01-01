package org.jacademie.tdweb.service.impl;

import org.jacademie.tdweb.service.MailService;
import org.springframework.transaction.annotation.Transactional;

import com.github.sendgrid.SendGrid;

@Transactional(readOnly = true)
public class MailServiceImpl implements MailService {

	private String username;
	
	private String password;

	private String from;
	
	private String webSiteUrl;
	
	
	@Override
	public void sendInvitationMail(String friend, String invitor, String league) {
		
		SendGrid sendGrid = new SendGrid(this.username, this.password);
		
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
