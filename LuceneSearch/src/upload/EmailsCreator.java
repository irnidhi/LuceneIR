package upload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import data.Email;

public class EmailsCreator {
	private ArrayList<Email> listDocuments= null;
	public ArrayList<Email> documenGenerator() {
		
		DBUploader uploader = new DBUploader();
		try {
			ResultSet rs = uploader.readDataBase();
			listDocuments = createDocumentList(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			uploader.closeConnection();	
		}
		
		return listDocuments;
		
	}
	
	public ArrayList<Email> createDocumentList(ResultSet resultSet) throws SQLException {
		Email email = null;
		int i=0;
		if (resultSet!=null){
			listDocuments = new ArrayList<Email>();
			while (resultSet.next()) {
				i++;
				String mId= resultSet.getString("mid");
				Date date= resultSet.getDate("date");
				String senderEmails= resultSet.getString("email_id") + " " +
						resultSet.getString("email2") + " " +
						resultSet.getString("email3") + " " +
						resultSet.getString("email4");
				String senderName= resultSet.getString("sender_last") + " " +
						resultSet.getString("sender_first");
				String senderStatus= resultSet.getString("sender_status");
				String recEmail= resultSet.getString("rvalue");
				String recName= resultSet.getString("rec_last") + " " +
						resultSet.getString("rec_first");
				String recStatus= resultSet.getString("rec_status");
				String subject= resultSet.getString("subject");
				String body= resultSet.getString("body");
				String type= resultSet.getString("rtype");
				
				email = new Email(mId, date, senderEmails, senderName, 
						senderStatus, recEmail, recName, recStatus, subject, body, type);
				System.out.println(i+ "--"+ email.toString());
				listDocuments.add(email);
			}
		}
		return listDocuments; 
	}
}
