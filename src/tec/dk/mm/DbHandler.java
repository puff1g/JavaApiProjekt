package tec.dk.mm;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

public class DbHandler {
	Connection conn = null;
	
	public void main(String[] args) {
		
	}
	
	public DbHandler(){
		
	}
	
	
	void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			
			String url1 = "jdbc:mysql://localhost:3306/java_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Forbindelse etableret til java_db!");
            }
		}
		catch(ClassNotFoundException ex) {
			Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch(SQLException ex) {
			Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void insertPerson(Person p) {
		String sql = "INSERT INTO `person`(`name`, `job`, `birthyear`, `driverlicense`, `gender`, `relationshipstatus`) VALUES (?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getJob());
			stmt.setInt(3, p.getBirthYear());
			stmt.setBoolean(4, p.isDriversLicense());
			stmt.setString(5, p.getGender());
			stmt.setString(6, p.getRelationshipStatus());
			
			stmt.executeUpdate();
			conn.close();
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	
	public String getPersons() {
		String sql = "SELECT * FROM `person`";
		String jsonString = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet response = stmt.executeQuery();
			
			
			while(response.next()) {
				Person person = new Person(response.getInt("id"), response.getInt("birthyear"), response.getString("name"), response.getString("job"), response.getString("gender"), response.getString("relationshipstatus"), response.getBoolean("driverlicense"));
				persons.add(person);
				
			}
			
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(persons);
			
			conn.close();
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	
	public String getSinglePerson(int id) {
		
		String sql = "SELECT * FROM `person` where `id` = " + id;
		String jsonString = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet response = stmt.executeQuery();
			
			
			
			while(response.next()) {
				Person person = new Person(response.getInt("id"), response.getInt("birthyear"), response.getString("name"), response.getString("job"), response.getString("gender"), response.getString("relationshipstatus"), response.getBoolean("driverlicense"));
				persons.add(person);
				ObjectMapper mapper = new ObjectMapper();
				jsonString = mapper.writeValueAsString(persons);
				// jsonString = "{{'name':'"+response.getString("name")+"', 'job':'"+response.getString("job")+"', 'birthyear':'"+response.getString("birthyear")+"', 'driverlicense':'"+response.getString("driverlicense")+"', 'gender':'"+response.getString("gender")+"', 'relationshipstatus':'"+response.getString("relationshipstatus")+"', }}";
			}
			
			conn.close();
			
			return jsonString;
			
			
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return jsonString;
		
		
		
	}
	
	
	
	public String removePerson(int id) {
		String sql = "DELETE FROM `person` WHERE id = "+id;
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
			
			conn.close();
			
			return "Handlingen blev udført!";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Fejl";
	}
	
	
	public boolean updatePerson(Person p) {
		System.out.println(p.getId());

			System.out.println("Update - bruger fundet");
			String sql = "UPDATE person SET name=?, job=?, gender=?, driverlicense=?, relationshipstatus=?, birthyear=? WHERE id=?";
			
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, p.getName());
				stmt.setString(2, p.getJob());
				stmt.setString(3, p.getGender());
				stmt.setBoolean(4, p.isDriversLicense());
				stmt.setString(5, p.getRelationshipStatus());
				stmt.setInt(6, p.getBirthYear());
				stmt.setInt(7, p.getId());
				
				int rowAffected = stmt.executeUpdate();
				System.out.println("row affected: " + rowAffected);
				
				conn.commit();
				conn.close();
				conn.commit();
				return true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
		
	}
	
	
	public boolean doesUserExist(int id) {
		String sql = "SELECT count(*) FROM `person` WHERE `id` = " + id;
		boolean wasfound = false;
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet response = stmt.executeQuery();
			response.next();
			int rows = response.getInt("count(*)");
			
			
			
			if(rows > 0) {
				
				wasfound = true;
			}
			else
			{
				wasfound = false;
			}
			
			conn.close();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(wasfound) {
			return true;
		}
		else
		{
			return false;
		}
	}
}
