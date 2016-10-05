package org.sociotech.communitymashup.source.researchgate.apiwrapper.items;


public class Author {
	private String firstname;
	
	private String lastname;
	
	private String id;
	
	
	public Author(String firstname, String lastname, String id) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		
		if(id != null)
			this.setID("rga_" + id);
		else
			this.setID("rga_" + (lastname.toLowerCase() + "_" + firstname.toLowerCase()).replaceAll(" ", "_"));
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}

	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
