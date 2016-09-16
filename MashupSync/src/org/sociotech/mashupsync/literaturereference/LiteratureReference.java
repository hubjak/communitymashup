package org.sociotech.mashupsync.literaturereference;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/**
 * Data structure to store a literature reference as used in the CommunityMashup
 * synchronization project.
 * @author Jakob Huber
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Reference")

public class LiteratureReference {
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement
	static class Author {
		@XmlAttribute
		private String firstName;
		
		@XmlAttribute
		private String lastName;
		
		public Author(String firstName, String lastName) {
			this.setFirstName(firstName);
			this.setLastName(lastName);
		}
		
		public Author() {
			
		}

		public String getFirstName() {
			return firstName;
		}
		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		
		public String getLastName() {
			return lastName;
		}
		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}		
	}
	
	private String title;
	
	@XmlElementWrapper(name = "Authors")
	@XmlElement(name = "Author")
	private LinkedList<Author> authors;
	
	@XmlAttribute
	private int year;
	
	private String source;
	
	private final static String DEFAULT_CHARSET = "UTF-8";
	
	public LiteratureReference(String title, int year) {
		this.setTitle(title);
		this.setYear(year);
		this.authors = new LinkedList<Author>();
	}
	
	public static LiteratureReference fromXml(String xml) {
		if(xml == null)
			return null;
		
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(LiteratureReference.class).createUnmarshaller();
			
			return (LiteratureReference) unmarshaller.unmarshal(new StringReader(xml));
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public String marshal() {
		try {
			Marshaller marshaller = JAXBContext.newInstance(LiteratureReference.class).createMarshaller();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshaller.marshal(this, baos);
			
			return baos.toString(DEFAULT_CHARSET);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public LiteratureReference() {
		this.authors = new LinkedList<Author>();
	}
	
	public LinkedList<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(LinkedList<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(String firstname, String lastname) {
		if(lastname != null && lastname.length() > 0)
			this.authors.add(new Author(firstname, lastname));
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
