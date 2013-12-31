package org.jacademie.tdweb.dto;

import org.apache.commons.codec.digest.DigestUtils;

public class LoginPasswordDTO {

	private String login;
	
	private String password;

	public LoginPasswordDTO() {
		super();
	}

	public String getEncryptedPassword() {
		return DigestUtils.md5Hex(password);
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
