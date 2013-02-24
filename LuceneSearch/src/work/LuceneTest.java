package work;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import data.Email;
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
