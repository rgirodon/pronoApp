package org.jacademie.tdweb.dto;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

public class ChangeMyPasswordDTO implements Serializable {

	private String oldPassword;
	
	private String newPassword;
	
	private String reEnterNewPassword;
	
	public ChangeMyPasswordDTO() {
		super();
	}
	
	public String getEncryptedOldPassword() {
		return DigestUtils.md5Hex(oldPassword);
	}
	
	public String getEncryptedNewPassword() {
		return DigestUtils.md5Hex(newPassword);
	}
	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getReEnterNewPassword() {
		return reEnterNewPassword;
	}

	public void setReEnterNewPassword(String reEnterNewPassword) {
		this.reEnterNewPassword = reEnterNewPassword;
	}

	
}
