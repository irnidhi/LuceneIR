package data;

import java.util.Date;

public class Email {
	private String mId;
	private Long date;
	private String dateFrom;
	private String dateTo;
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
	
	public Email(String mId, String dateFrom, String dateTo, String senderEmails, String senderName,
			String senderStatus, String recEmail, String recName,
			String recStatus, String subject, String body, String type) {

		this.mId = mId;
		this.dateFrom = dateFrom.trim();
		this.dateTo =dateTo.trim();
		this.senderEmails = senderEmails.trim();
		this.senderName = senderName.trim();
		this.senderStatus = senderStatus.trim();
		this.recEmail = recEmail.trim();
		this.recName = recName.trim();
		this.recStatus = recStatus.trim();
		this.subject = subject.trim();
		this.body = body.trim();
		this.type = type.trim();

	}
	
	public Email(String mId, Date date, String senderEmails, String senderName,
			String senderStatus, String recEmail, String recName,
			String recStatus, String subject, String body, String type) {

		this.mId = mId;
		this.date = date.getTime();
		this.senderEmails = senderEmails.trim();
		this.senderName = senderName.trim();
		this.senderStatus = senderStatus.trim();
		this.recEmail = recEmail.trim();
		this.recName = recName.trim();
		this.recStatus = recStatus.trim();
		this.subject = subject.trim();
		this.body = body.trim();
		this.type = type.trim();

	}
	@Override
	public String toString() {
		return "Email [mId=" + mId + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", senderEmails="
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

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	
	
}
