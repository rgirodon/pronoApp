package org.jacademie.tdweb.dto;

import java.io.Serializable;

public class NotificationDTO implements Serializable {

	private String subject;
	
	private String text;
	
	public NotificationDTO() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
