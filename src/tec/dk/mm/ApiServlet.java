package tec.dk.mm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;


public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ApiServlet() {
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		
	
		
		ArrayList<Elev> elever = new ArrayList<Elev>();
		
		DbHandler handler = new DbHandler();
		handler.openConnection();
		
		
		
				
			case PersonSingleID: 
				var spid = ar.getId();
				out.println(handler.getSinglePerson(spid));
				break;
				
			case PersonGetAll: 
				var iii = handler.getPersons();
				out.println(iii);
				
				break;
				
			case PersonNotFound: 
				out.println("<br>Ingen personer fundet!");
				break;

		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHandler handler = new DbHandler();
		handler.openConnection();
		
		String reader = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		ObjectMapper mapper = new ObjectMapper();
		
		Person person = mapper.readValue(reader, Person.class);
		
		System.out.println("POST DETECTED - TILFØJER BRUGER!");
		
		handler.insertPerson(person);
	}
	
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHandler handler = new DbHandler();
		handler.openConnection();
		
		AnalyzeRequest ar = new AnalyzeRequest(req.getPathInfo());
		switch (ar.getLevel()){
			case PersonSingleID: 
				var spid = ar.getId();
				System.out.println(spid);
				handler.removePerson(spid);
			break;
			
			case PersonGetAll: 
				System.out.println("Du kan kun slette en bruger per kald.");
				
				break;
				
			case PersonNotFound: 
				System.out.println("Ingen bruger fundet med den ID!");
				break;
		};	
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DbHandler handler = new DbHandler();
		handler.openConnection();
		
		String reader = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		System.out.println("========================================");
		System.out.println(reader);
		System.out.println("========================================");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Person person = mapper.readValue(reader, Person.class);
		
		handler.updatePerson(person);
	}
	
	

}
