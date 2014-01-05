package org.jacademie.tdweb.dto;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

public class ChangeMyDisplayNameDTO implements Serializable {

	private String displayNameInput;
	
	public ChangeMyDisplayNameDTO() {
		super();
	}

	public String getDisplayNameInput() {
		return displayNameInput;
	}

	public void setDisplayNameInput(String displayNameInput) {
		this.displayNameInput = displayNameInput;
	}

}
