package org.sociotech.communitymashup.source.researchgate.apiwrapper.items;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Author {
	@XmlAttribute
	private String firstname;
	
	@XmlAttribute
	private String lastname;
	
	
	public Author(String firstname, String lastname) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
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
