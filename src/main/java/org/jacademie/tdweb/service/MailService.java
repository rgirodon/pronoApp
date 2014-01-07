package org.jacademie.tdweb.service;

import org.jacademie.tdweb.dto.NotificationDTO;

public interface MailService {

	void sendInvitationMail(String friend, String invitor, String league);

	void sendLeagueUsersNotification(Integer leagueId, NotificationDTO notificationDTO);
}
