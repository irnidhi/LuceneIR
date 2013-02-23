package upload;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class DocumentCreator {
	private ArrayList<Document> listDocuments= null;
	private HashMap<String,String> map = null;

	public DocumentCreator() {
		super();
	}
	
	public DocumentCreator(HashMap<String, String> map) {
		super();
		this.map = map;
	}

	public ArrayList<Document> documentGenerator() {
		DBUploader uploader = new DBUploader();
		try {
			ResultSet rs = uploader.readDataBase();
			listDocuments = createDocumentList(rs, map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			uploader.closeConnection();	
		}
		
		return listDocuments;
		
	}
	
	public ArrayList<Document> createDocumentList(ResultSet resultSet, HashMap<String, String> map) throws SQLException {
		Document doc = null;
		System.out.println("Start populating document list :" + Calendar.getInstance().getTime());
		if (resultSet!=null){
			listDocuments = new ArrayList<Document>();
			String oldMId ="-999";
			String newMId ="-999";
			String recEmails ="";
			String recNames = "";
			String recStatuses = "";
			while (resultSet.next()) {
				newMId = resultSet.getString("mid");
				if (!newMId.equals(oldMId)){
					if (doc!=null){
						doc.add(new StringField(map.get("mId"), newMId, Field.Store.YES));
						doc.add(new TextField(map.get("recEmail"), recEmails, Field.Store.YES));
						doc.add(new TextField(map.get("recName"), recNames, Field.Store.YES));
						doc.add(new TextField(map.get("recStatus"), recStatuses, Field.Store.YES));
						listDocuments.add(doc);
						doc = null;
					}
					
					TextField tField = null;
					recEmails ="";
					recNames = "";					
					doc = new Document();
					Date date= resultSet.getDate("date");
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					doc.add(new StringField(map.get("date"), df.format(date),Field.Store.YES));
					doc.add(new TextField(map.get("senderEmails"), resultSet.getString("email_id") + " " +
							resultSet.getString("email2") + " " +
							resultSet.getString("email3") + " " +
							resultSet.getString("email_id"), Field.Store.YES));
					doc.add(new TextField(map.get("senderName"), resultSet.getString("sender_last") + " " +
							resultSet.getString("sender_first"), Field.Store.YES));
					doc.add(new TextField(map.get("senderStatus"), resultSet.getString("sender_status"), Field.Store.YES));
					recEmails = resultSet.getString("rvalue");
					recNames = resultSet.getString("rec_last") + " " +
							resultSet.getString("rec_first");
					recStatuses = resultSet.getString("rec_status");
					tField = new TextField(map.get("subject"), resultSet.getString("subject"), Field.Store.YES);
					tField.setBoost((float) 1.2);
					doc.add(tField);
					tField = new TextField(map.get("body"), resultSet.getString("body"), Field.Store.YES);
					tField.setBoost((float) 1.2);
					doc.add(tField);
					oldMId = newMId;
					//doc.add(new TextField("type", resultSet.getString("rtype"), Field.Store.YES));
				} else {
					recEmails = recEmails + " " + resultSet.getString("rvalue");
					recEmails = recStatuses + " " + resultSet.getString("rec_status");
					recNames = recNames + " , " + resultSet.getString("rec_last") + " " +
							resultSet.getString("rec_first");
				}
			}
			if (doc!=null){
				doc.add(new StringField(map.get("mId"), newMId, Field.Store.YES));
				doc.add(new TextField(map.get("recEmail"), recEmails, Field.Store.YES));
				doc.add(new TextField(map.get("recName"), recNames, Field.Store.YES));
				doc.add(new TextField(map.get("recStatus"), recStatuses, Field.Store.YES));
				listDocuments.add(doc);
				doc=null;
			}			
			System.out.println("ArrayList size = "+ listDocuments.size());
		}
		System.out.println("Done populating document list :" + Calendar.getInstance().getTime());
		return listDocuments; 
	}
}
