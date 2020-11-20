package tec.dk.mm;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AnalyzeRequest {

	MatchEnum level;
	int id;
	String action;
	
	
	public AnalyzeRequest(String pathInfo) {
			
		Matcher matcherPerson = Pattern.compile("(?i)^/person/([0-9]+)$").matcher(pathInfo);
		
	
		
		if(matcherPerson.find()) {
			System.out.println("ID found in URL");
			// Henter single person out
			level = MatchEnum.PersonSingleID;
			id = Integer.parseInt(matcherPerson.group(1));
			
		}
		else {
			matcherPerson = Pattern.compile("(?i)^/person/?$").matcher(pathInfo);
			if(matcherPerson.find()) {
				// Hent alle brugere
				System.out.println("Alle Brugere");
				level = MatchEnum.PersonGetAll;
				// id = Integer.parseInt(matcherPerson.group(1));
			}
			else {
				// Ingen bruger fundet
				System.out.println("Ingen ID fundet!");
				level = MatchEnum.PersonNotFound;
				
			}
		}
		
	}
	
	public MatchEnum getLevel() {
		return level;
	}
	
	public int getId() {
		return id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	
	
}
