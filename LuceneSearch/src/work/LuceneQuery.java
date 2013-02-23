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

public class LuceneQuery {
    private IndexReader reader = null;
    private IndexSearcher searcher = null;
    private int hitsPerPage = 10;
    private TopScoreDocCollector collector = null;
    private HashMap<String,String> map = null;
    
    
	public LuceneQuery(HashMap<String, String> map) {
		super();
		this.map = map;
	}

	/*
	public void AssistedQuery(String dateFrom, String dateTo, String senderEmails, String senderName, String senderStatus, String recEmail, String recName, String recStatus, String subject, String body, StandardAnalyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("ASSISTED SEARCH PARAMETER");
		System.out.println("Date = From " + dateFrom + " until " + dateTo);
		System.out.println("Sender email = " + senderEmails);
		System.out.println("Sender Name = " + senderName);
		System.out.println("Sender Position = " + senderStatus);
		System.out.println("Receiver Email = " + recEmail);
		System.out.println("Receiver Name = " + recName);
		System.out.println("Receiver Position = " + recStatus);
		System.out.println("Subject = " + subject);
		System.out.println("Body = " + body);
		
		String queryAss = "";
		String or = "";
		if(!senderEmails.equals("")) {
			if(!senderName.equals("")) or = " AND ";
			queryAss = queryAss + " senderEmails:" + senderEmails + or;
			or = "";
		}
		if(!senderName.equals("")) {
			if(!senderStatus.equals("")) or = " AND ";
			queryAss = queryAss + "senderName:" + senderName + or;
			or = "";
		}
		if(!senderStatus.equals("")) {
			if(!recEmail.equals("")) or = " AND ";
			queryAss = queryAss + "senderStatus:" + senderStatus + or;
			or = "";
		}
		if(!recEmail.equals("")) {
			if(!recName.equals("")) or = " AND ";
			queryAss = queryAss + "recEmail:" + recEmail + or;
			or = "";
		}
		if(!recName.equals("")) {
			if(!recStatus.equals("")) or = " AND ";
			queryAss = queryAss + "recName:" + recName + or;
			or = "";
		}
		if(!recStatus.equals("")) {
			if(!subject.equals("")) or = " AND ";
			queryAss = queryAss + "recStatus:" + recStatus + or;
			or = "";
		}
		if(!subject.equals("")) {
			if(!body.equals("")) or = " AND ";
			queryAss = queryAss + "subject:" + subject + or;
			or = "";
		}
		if(!body.equals("")) {
			queryAss = queryAss + "body:" + body;
		}

		//tes date
		//queryAss = "date:[" + dateFrom + " TO " + dateTo + "]"; 
		queryAss = "Dickson";
		System.out.println("Query = " + queryAss);
		Query q = new QueryParser(Version.LUCENE_40, "body", analyzer).parse(queryAss);
	    
	    reader = DirectoryReader.open(index);
	    searcher = new IndexSearcher(reader);
	    collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    displayResult();
	}
	
	private void displayResult() throws IOException {
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;
	    int topHit = 10;
	    System.out.println("6-" + Calendar.getInstance().getTime());
	    System.out.println("Found " + hits.length + " hits.");
	    System.out.println("Top " + topHit +" results");
	    for(int i=0;i<topHit;++i) {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      System.out.println((i + 1) + ". " + d.get("senderName") + "\t" + d.get("mId") + "\t" + d.get("date"));
	    }
	*/
	public ArrayList<Document>  assistedQuery(String[] queryInput, Analyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("Query = " + queryInput.toString());
		String queryStr = assistedQueryConstructor(queryInput);
	    System.out.println("Query = " + queryStr);		
			
	    ArrayList<Document> listRes = null;
		Query q = new QueryParser(Version.LUCENE_41, "body", analyzer).parse(queryStr);
	    
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
	    return listRes;
	}
	    
	public ArrayList<Document> simpleQuery(String queryStr, Analyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("Query = " + queryStr);
		queryStr = simpleQueryConstructor(queryStr);
	    System.out.println("Query = " + queryStr);
	    
	    ArrayList<Document> listRes = null;
		Query q = new QueryParser(Version.LUCENE_40, "body", analyzer).parse(queryStr);
	    
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
	    		
	    return listRes;
	}
	

	public void close() throws IOException{
		reader.close();
	};
	
	private String simpleQueryConstructor(String query){
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
	
	private String assistedQueryConstructor(String[] queryInput){
		
		//input check
		for(int i=0;i<10;i++){
			while (!queryInput[i].equals("")){
				if(queryInput[i].substring(0, 1).equals("*")) queryInput[i] = queryInput[i].substring(1);
				else break;
			}
			while (!queryInput[i].equals("")){
				if(queryInput[i].substring(0, 1).equals("?")) queryInput[i] = queryInput[i].substring(1);
				else break;
			}
		}
		
		//build query
		String queryAss = "";
		if(!queryInput[0].equals("")){
			queryAss = queryAss + "date:[" + queryInput[0] + " TO " + queryInput[1] + "]";
		}
		if(!queryInput[2].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + " senderEmails:" + queryInput[2];
		}
		if(!queryInput[3].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "senderName:" + queryInput[3];
		}
		if(!queryInput[4].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "senderStatus:" + queryInput[4];
		}
		if(!queryInput[5].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "recEmail:" + queryInput[5];
		}
		if(!queryInput[6].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "recName:" + queryInput[6];
		}
		if(!queryInput[7].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "recStatus:" + queryInput[7];
		}
		if(!queryInput[8].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "subject:" + queryInput[8];
		}
		if(!queryInput[9].equals("")) {
			if(!queryAss.equals("")) queryAss += " AND ";
			queryAss = queryAss + "body:" + queryInput[9];
		}

		return queryAss;
	}
}
