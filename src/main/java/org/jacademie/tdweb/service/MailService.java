package org.jacademie.tdweb.service;

public interface MailService {

	void sendInvitationMail(String friend, String invitor, String league);
}
