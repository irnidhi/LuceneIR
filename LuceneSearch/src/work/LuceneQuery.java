package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import data.Email;

public class LuceneQuery {
    private IndexReader reader = null;
    private IndexSearcher searcher = null;
    private int hitsPerPage = 10;
    private TopScoreDocCollector collector = null;
    private HashMap<String,String> map = null;
    private Query q=null;
    
    
	public LuceneQuery(HashMap<String, String> map) {
		super();
		this.map = map;
	}

	public ArrayList<Document>  assistedQuery(Email email, Analyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("Query = " + email.toString());
		String queryStr = assistedQueryConstructor(email);
	    System.out.println("Query = " + queryStr);		
			
	    ArrayList<Document> listRes = null;
	    if (!queryStr.equals("")){
			q = new QueryParser(Version.LUCENE_41, "body", analyzer).parse(queryStr);
		    
		    reader = DirectoryReader.open(index);
		    searcher = new IndexSearcher(reader);
		    collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(q, collector);
		    if (searcher!=null){
			    ScoreDoc[] hits = collector.topDocs().scoreDocs;
			    System.out.println("Found " + hits.length + " hits.");
			    listRes = new ArrayList<Document>();
			    for(int i=0;i<hits.length;++i) {
			      int docId = hits[i].doc;
			      Document d = searcher.doc(docId);
			      System.out.println((i + 1) + ". " + d.get("senderName") + "\t" + d.get("mId") + "\t" + d.get("date"));
			      listRes.add(d);
			     
			    }
		    }
	    }
	    return listRes;
	}
	    
	public ArrayList<Document> simpleQuery(String queryStr, Analyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("Query = " + queryStr);
		ArrayList<Document> listRes = null;
		if (!queryStr.equals("")){
			queryStr = simpleQueryConstructor(queryStr);
		    System.out.println("Query = " + queryStr);
		    
		    
			q = new QueryParser(Version.LUCENE_40, "body", analyzer).parse(queryStr);
		    
		    reader = DirectoryReader.open(index);
		    searcher = new IndexSearcher(reader);
		    collector = TopScoreDocCollector.create(hitsPerPage, true);
		    searcher.search(q, collector);
		    
		    if (searcher!=null){
			    ScoreDoc[] hits = collector.topDocs().scoreDocs;
			    System.out.println("Found " + hits.length + " hits.");
			    listRes = new ArrayList<Document>();
			    for(int i=0;i<hits.length;++i) {
			      int docId = hits[i].doc;
			      Document d = searcher.doc(docId);
			      System.out.println((i + 1) + ". " + d.get("senderName") + "\t" + d.get("mId"));
			      listRes.add(d);
			     
			    }
		    	
		    }
		}	
	    return listRes;
	}
	

	public void close() throws IOException{
		reader.close();
	};
	
	private String simpleQueryConstructor(String query){
	
		while (!query.equals("") && (query.substring(0, 1).equals("*") || query.substring(0, 1).equals("?"))) {
			query = query.substring(1);
		}
		

		String queryOut = "";
		boolean found=false;
		Iterator<Entry<String, String>> iter = map.entrySet().iterator();
		
		while (iter.hasNext() && !found){
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iter.next(); 
			String value = (String) entry.getValue();
			if (!queryOut.equals(""))
				queryOut = queryOut + " OR " + value +":" + query;
			else
				queryOut = queryOut + value +":" + query;	
			value= value + ":";
			found = query.toLowerCase().contains(value.toLowerCase());
		}
		
		
		if (!found)
			return queryOut;
		else
			return query;
	}
	
	private String assistedQueryConstructor(Email email){

		//build query
		String queryAss = "";
		if (email!=null){
			if((!email.getDateFrom().equals("")) && (!email.getDateTo().equals(""))){
				queryAss = queryAss + map.get("date") +":[" + email.getDateFrom() + " TO " + email.getDateTo() + "]";
			}
			//if(!email.getSenderEmails().equals("")) {
			//	if(!queryAss.equals("")) queryAss += " AND ";
			//	queryAss = queryAss + map.get("senderEmails") +":" + email.getSenderEmails();
			//}
			if(!email.getSenderName().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + "((" +map.get("senderName") +":" + email.getSenderName() +
				") OR (" +map.get("senderEmails") +":" + email.getSenderName() + "))";
			}
			if(!email.getSenderStatus().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + map.get("senderStatus") +":" + email.getSenderStatus();
			}
			//if(!email.getRecEmail().equals("")) {
			//	if(!queryAss.equals("")) queryAss += " AND ";
			//	queryAss = queryAss + map.get("recEmail") +":" + email.getRecEmail();
			//}
			if(!email.getRecName().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + "((" + map.get("recName") +":" + email.getRecName() +
						") OR (" +map.get("recEmail") +":" + email.getRecName() + "))";
			}
			if(!email.getRecStatus().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + map.get("recStatus") +":" + email.getRecStatus();
			}
			if(!email.getSubject().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + map.get("subject") +":" + email.getSubject();
			}
			if(!email.getBody().equals("")) {
				if(!queryAss.equals("")) queryAss += " AND ";
				queryAss = queryAss + map.get("body") +":" + email.getBody();
			}
		}
		return queryAss;
	}
}
