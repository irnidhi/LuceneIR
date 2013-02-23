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

import data.Email;

import upload.DocumentCreator;

public class LuceneTest {

	private static LuceneSearch lSearch = null;
	
	public static void main(String[] args) throws IOException, ParseException {
		lSearch = new LuceneSearch();
		lSearch.buildIndex();
		//tes standard query 1
		 String queryStr = "Dicks*";
		 lSearch.standardQuery(queryStr);
		//tes standard query 2
		 queryStr = "(senderName:Dickson OR senderStatus:hobo)";
		 lSearch.standardQuery(queryStr);		 
		//tes assisted query
		// Email email = new Email("", "19980101000000","20050101000000", "dic*", "Dick*",  "**", "  ", "*", "??", "?",  "enron", "");
		//lSearch.assistedQuery(email);

	}
}
