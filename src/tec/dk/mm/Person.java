package tec.dk.mm;

public class Person {
	
	
	private int id, birthYear;
	private String name, job, gender, relationshipStatus;
	private boolean driversLicense;
	
	public Person(int id, int birthYear, String name, String job, String gender, String relationshipStatus,
			boolean driversLicense) {

		this.id = id;
		this.birthYear = birthYear;
		this.name = name;
		this.job = job;
		this.gender = gender;
		this.relationshipStatus = relationshipStatus;
		this.driversLicense = driversLicense;
	}
	
	
	public Person() {
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRelationshipStatus() {
		return relationshipStatus;
	}
	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}
	public boolean isDriversLicense() {
		return driversLicense;
	}
	public void setDriversLicense(boolean driversLicense) {
		this.driversLicense = driversLicense;
	}

}
