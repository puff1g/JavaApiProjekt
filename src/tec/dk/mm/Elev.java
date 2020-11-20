package tec.dk.mm;

import java.util.ArrayList;

public class Elev {
	int id, alder;
	String navn, email, telefon;
	
	ArrayList<String> skills;
	
	public Elev(int id, String navn, int alder, String email, String telefon, String skill) {
		this.id = id;
		this.navn = navn;
		this.alder = alder;
		this.email = email;
		this.telefon = telefon;
		skills = new ArrayList<String>();
		skills.add(skill);
	}
	
	public void addSkill(String skill) {
		skills.add(skill);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlder() {
		return alder;
	}

	public void setAlder(int alder) {
		this.alder = alder;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public ArrayList<String> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<String> skills) {
		this.skills = skills;
	}
}
