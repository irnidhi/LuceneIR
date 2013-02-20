package data;

import java.util.Date;

public class Email {
	private String mId;
	private Long date;
	private String senderEmails;
	private String senderName;
	private String senderStatus;
	private String recEmail;
	private String recName;
	private String recStatus;
	private String subject;
	private String body;
	private String type;
	
	public Email(){
	}
	
	public Email(String mId, Long date, String senderEmails, String senderName,
			String senderStatus, String recEmail, String recName,
			String recStatus, String subject, String body, String type) {

		this.mId = mId;
		this.date = date;
		this.senderEmails = senderEmails;
		this.senderName = senderName;
		this.senderStatus = senderStatus;
		this.recEmail = recEmail;
		this.recName = recName;
		this.recStatus = recStatus;
		this.subject = subject;
		this.body = body;
		this.type = type;
	}
	
	public Email(String mId, Date date, String senderEmails, String senderName,
			String senderStatus, String recEmail, String recName,
			String recStatus, String subject, String body, String type) {

		this.mId = mId;
		this.date = date.getTime();
		this.senderEmails = senderEmails;
		this.senderName = senderName;
		this.senderStatus = senderStatus;
		this.recEmail = recEmail;
		this.recName = recName;
		this.recStatus = recStatus;
		this.subject = subject;
		this.body = body;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Email [mId=" + mId + ", date=" + date + ", senderEmails="
				+ senderEmails + ", senderName=" + senderName
				+ ", senderStatus=" + senderStatus + ", recEmail=" + recEmail
				+ ", recName=" + recName + ", recStatus=" + recStatus
				+ ", subject=" + subject + ", body=" + body + ", type=" + type
				+ "]";
	}

	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	
	public void setDate(Date date) {
		this.date = date.getTime();
	}
	
	public String getSenderEmails() {
		return senderEmails;
	}
	public void setSenderEmails(String senderEmails) {
		this.senderEmails = senderEmails;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSenderStatus() {
		return senderStatus;
	}
	public void setSenderStatus(String senderStatus) {
		this.senderStatus = senderStatus;
	}
	public String getRecEmail() {
		return recEmail;
	}
	public void setRecEmail(String recEmail) {
		this.recEmail = recEmail;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
