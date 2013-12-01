package org.jacademie.tdweb.dto;

import java.io.Serializable;

public class RegisterDTO implements Serializable {

	private String login;
	
	private String password;
	
	private String reEnterPassword;
	
	public RegisterDTO() {
		super();
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

	public String getReEnterPassword() {
		return reEnterPassword;
	}

	public void setReEnterPassword(String reEnterPassword) {
		this.reEnterPassword = reEnterPassword;
	}
}
