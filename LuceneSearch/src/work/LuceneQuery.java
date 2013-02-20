package work;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

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

	public void simpleQuery(String queryStr, StandardAnalyzer analyzer, Directory index) throws IOException, ParseException {
		System.out.println("Query = " + queryStr);
		queryStr = simpleQueryConstructor(queryStr);
	    System.out.println("Query = " + queryStr);
		Query q = new QueryParser(Version.LUCENE_40, "body", analyzer).parse(queryStr);
	    
	    reader = DirectoryReader.open(index);
	    searcher = new IndexSearcher(reader);
	    collector = TopScoreDocCollector.create(hitsPerPage, true);
	    searcher.search(q, collector);
	    displayResult();
	}
	
	private void displayResult() throws IOException {
	    ScoreDoc[] hits = collector.topDocs().scoreDocs;
	    
	    System.out.println("6-" + Calendar.getInstance().getTime());
	    System.out.println("Found " + hits.length + " hits.");
	    for(int i=0;i<hits.length;++i) {
	      int docId = hits[i].doc;
	      Document d = searcher.doc(docId);
	      System.out.println((i + 1) + ". " + d.get("senderName") + "\t" + d.get("mId"));
	    }
		
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
}
