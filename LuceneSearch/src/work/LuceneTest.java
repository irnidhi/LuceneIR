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

import analyze.SynonymAnalyzer;

import data.Email;

import upload.DocumentCreator;

public class LuceneTest {

	private static LuceneSearch lSearch = null;
	
	public static void main(String[] args) throws IOException, ParseException {
		lSearch = new LuceneSearch();
		try {
			lSearch.buildIndex();
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tes standard query 1
		 String queryStr = "Dicks*";
		 lSearch.standardQuery(queryStr);
		//tes standard query 2
		 queryStr = "(senderName:Dick* OR subject:person)";
		 lSearch.standardQuery(queryStr);		 
		//tes assisted query
		Email email = new Email("", "","", "dic*", "dic*",  "", " ", "", "", "",  "enron", "");
		lSearch.assistedQuery(email);
	}
}
