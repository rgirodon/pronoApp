package org.jacademie.tdweb.dto;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

public class RegisterDTO implements Serializable {

	private String login;
	
	private String displayName;
	
	private String password;
	
	private String reEnterPassword;
	
	public RegisterDTO() {
		super();
	}
	
	public String getEncryptedPassword() {
		return DigestUtils.md5Hex(password);
	}
	
	public String getReEnterEncryptedPassword() {
		return DigestUtils.md5Hex(reEnterPassword);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}
}
