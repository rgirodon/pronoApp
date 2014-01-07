package org.jacademie.tdweb.sendgrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONObject;

import com.github.kevinsawicki.http.HttpRequest;

public class MultipleBccSendGrid {

	private static final String PARAM_API_USER    = "api_user";
	private static final String PARAM_API_KEY     = "api_key";
	private static final String PARAM_TOS         = "to[]";
	private static final String PARAM_TONAMES     = "toname[]";
	private static final String PARAM_FROM        = "from";
	private static final String PARAM_FROMNAME    = "fromname";
	private static final String PARAM_REPLYTO     = "replyto";
	private static final String PARAM_SUBJECT     = "subject";
	private static final String PARAM_HTML        = "html";
	private static final String PARAM_TEXT        = "text";
	private static final String PARAM_FILES       = "files[%s]";
	private static final String PARAM_HEADERS     = "headers";
	private static final String PARAM_BCCS        = "bcc[]";

	private String username;
	private String password;
	private ArrayList<String> tos = new ArrayList<String>();
	private ArrayList<String> tonames = new ArrayList<String>();
	private ArrayList<String> bccs = new ArrayList<String>();
	private String from;
	private String fromname;
	private String replyto;
	private String subject;
	private String text;
	private String html;
	private ArrayList<Attachment> attachments = new ArrayList<Attachment>();
	

	private JSONObject headers = new JSONObject();

	public MultipleBccSendGrid(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String send() {
		return this.web();
	} 

	public String web() {
		HttpRequest request = HttpRequest.post("https://api.sendgrid.com/api/mail.send.json");

		if (this.username != null) {
			request.part(PARAM_API_USER,  this.username);
		}
		if (this.password != null) {
			request.part(PARAM_API_KEY,   this.password);
		}
		for (String to:this.getTos()) {
			request.part(PARAM_TOS,     to);
		}
		for (String toname:this.getToNames()) {
			request.part(PARAM_TONAMES,     toname);
		}
		for (String bcc:this.getBccs()) {
			request.part(PARAM_BCCS,     bcc);
		}
		if (this.getFrom() != null) {
			request.part(PARAM_FROM,      this.getFrom());
		}
		if (this.getFromName() != null) {
			request.part(PARAM_FROMNAME,  this.getFromName());
		}
		if (this.getReplyTo() != null) {
			request.part(PARAM_REPLYTO, this.getReplyTo());
		}
		if (this.getSubject() != null) {
			request.part(PARAM_SUBJECT,   this.getSubject());
		}
		if (this.getText() != null) {
			request.part(PARAM_TEXT,    this.getText());
		}
		if (this.getHtml() != null) {
			request.part(PARAM_HTML,    this.getHtml());
		}
		for (Attachment attachment:this.getAttachments()) {
			request.part(String.format(PARAM_FILES, attachment.name), attachment.contents);
		}
		request.part(PARAM_HEADERS,   this.getHeaders().toString());

		return request.body();
	}

	public ArrayList<String> getTos() {
		return this.tos;
	}

	public ArrayList<String> getToNames() {
		return this.tonames;
	}

	public ArrayList<String> getBccs() {
		return this.bccs;
	}

	public String getFrom() {
		return this.from;
	}

	public String getFromName() {
		return this.fromname;
	}

	public String getReplyTo() {
		return this.replyto;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getText() {
		return this.text;
	}

	public String getHtml() {
		return this.html;
	}

	public ArrayList<Attachment> getAttachments() {
		return this.attachments;
	}

	public JSONObject getHeaders() {
		return this.headers;
	}

	public MultipleBccSendGrid addTo(String email) {
		this.tos.add(email);
		return this;
	}

	public MultipleBccSendGrid addToName(String name) {
		this.tonames.add(name);
		return this;
	}
	
	public MultipleBccSendGrid addBcc(String email) {
		this.bccs.add(email);
		return this;
	}

	public MultipleBccSendGrid setFrom(String email) {
		this.from = email;
		return this;
	}

	public MultipleBccSendGrid setFromName(String name) {
		this.fromname = name;
		return this;
	}

	public MultipleBccSendGrid setReplyTo(String email) {
		this.replyto = email;
		return this;
	}

	public MultipleBccSendGrid setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public MultipleBccSendGrid setText(String text) {
		this.text = text;
		return this;
	}

	public MultipleBccSendGrid setHtml(String html) {
		this.html = html;
		return this;
	}

	public MultipleBccSendGrid addFile(Attachment attachment) {
		this.addAttachment(attachment);
		return this;
	}

	public MultipleBccSendGrid addFile(File file) throws FileNotFoundException {
		this.addAttachment(file);
		return this;
	}

	public MultipleBccSendGrid addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
		return this;
	}

	public MultipleBccSendGrid addAttachment(File file) throws FileNotFoundException {
		MultipleBccSendGrid.Attachment attachment = new MultipleBccSendGrid.Attachment(file);
		this.addAttachment(attachment);
		return this;
	}

	public MultipleBccSendGrid addHeader(String key, String value) {
		try {
			this.headers.put(key, value);
		} catch(Exception e){
			e.printStackTrace();
		}

		return this;
	}

	public static class Attachment {
		public final String name;
		public final InputStream contents;

		public Attachment(File file) throws FileNotFoundException {
			this.name = file.getName();
			this.contents = new FileInputStream(file);
		}

		public Attachment(String name, InputStream contents) {
			this.name = name;
			this.contents = contents;
		}
	}
}
