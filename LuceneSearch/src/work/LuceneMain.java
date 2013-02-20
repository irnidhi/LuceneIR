package work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import upload.DocumentCreator;

public class LuceneMain {
	private static StandardAnalyzer analyzer = null;
	private static Directory index = null;
	private static IndexWriterConfig config = null;
	private static IndexWriter w = null;
	private static HashMap<String,String> docMap = null;
	
	public static void main(String[] args) throws IOException, ParseException {
		System.out.println("1-" + Calendar.getInstance().getTime());
	    initializeProp();
	    DocumentCreator docCreator = new DocumentCreator(docMap);
	    ArrayList<Document> listDocuments = docCreator.documentGenerator();
	    System.out.println("4-" + Calendar.getInstance().getTime());
	    initializeIndex();
	    addDocList(w, listDocuments);
	    w.close();
	    System.out.println("5-" + Calendar.getInstance().getTime());
	    

	    // 2. query
	    //String queryStr = args.length > 0 ? args[0] : "(senderName:Dickson OR senderStatus:hobo)";
	    String queryStr = args.length > 0 ? args[0] : "(senderName:Dicks*) OR \"new york\"";
	    LuceneQuery lQuery = new LuceneQuery(docMap);
	    lQuery.simpleQuery(queryStr, analyzer, index);
	    // reader can only be closed when there
	    // is no need to access the documents any more.
	    lQuery.close();
	    System.out.println("7-" + Calendar.getInstance().getTime());

	  }

	  	private static void addDocList(IndexWriter w, ArrayList<Document> list) throws IOException{
	  		Document doc =null;
	  		if (list!=null){
	  			Iterator<Document> iter = list.iterator();
	  			while (iter.hasNext()) {
	  				doc = iter.next();
	  				w.addDocument(doc);
	  			}
	  			
	  		}
	  	}
	  	
	  	private static void initializeIndex() throws IOException{
		    // 0. Specify the analyzer for tokenizing text.
		    //    The same analyzer should be used for indexing and searching
		    // 1. create the index
		    analyzer = new StandardAnalyzer(Version.LUCENE_40);
		    index = new RAMDirectory();
		    
		    config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
		    w = new IndexWriter(index, config);	  		
		    
	  	}

	  	private static void initializeProp() {
	  		docMap = new HashMap<String, String>();
	  		docMap.put("mId", "mId");
	  		docMap.put("date", "date");
	  		docMap.put("senderEmails", "senderEmails");
	  		docMap.put("senderName", "senderName");
	  		docMap.put("senderStatus", "senderStatus");
	  		docMap.put("subject", "subject");
	  		docMap.put("body", "body");
	  		docMap.put("recEmail", "recEmail");
	  		docMap.put("recName", "recName");
	  		docMap.put("recStatus", "recStatus");
	  		docMap.put("recStatus", "recStatus");
	  	}

}
