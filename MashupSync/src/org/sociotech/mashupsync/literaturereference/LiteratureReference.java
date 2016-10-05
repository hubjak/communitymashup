package org.sociotech.mashupsync.literaturereference;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

/**
 * This data structure is used by the CommunityMashup as well as the CommunityMashup synchronization 
 * tool. It represents a single literature reference with an ordered list of authors.
 * 
 * @author Jakob Huber
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Reference")

public class LiteratureReference {
	
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement
	public static class Author {
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
		
		public String toString() {
			return this.lastName + ", " + this.firstName;
		}
		
		public boolean isSamePerson(String firstname, String lastname) {
			if(!Normalizer.normalize(lastname, Normalizer.Form.NFC)
					.equals(Normalizer.normalize(this.getLastName(), Normalizer.Form.NFC))) return false;
			
			String[] firstNamesC = Normalizer.normalize(firstname.toLowerCase(), Normalizer.Form.NFC).split(" ");
			String[] firstNamesE = Normalizer.normalize(this.getFirstName().toLowerCase(), Normalizer.Form.NFC).split(" ");
			
			// Check if the two persons' first names are equal
			for(int j = 0; j < Math.min(firstNamesC.length, firstNamesE.length); j++) {
				if((firstNamesC[j].contains(".") || firstNamesE[j].endsWith(".")) && 
						firstNamesC[j].charAt(0) != firstNamesE[j].charAt(0)) return false;
				
				if(!firstNamesC[j].equals(firstNamesE[j])) return false;
				
			}
			
			return true;
		}
		
		@Override
		public boolean equals(Object o) {
			return o instanceof Author && this.isSamePerson(((Author) o).getFirstName(), ((Author) o).getLastName());
		}
	}
	
	private String title;
	
	@XmlElementWrapper(name = "Authors")
	@XmlElement(name = "Author")
	private LinkedList<Author> authors;
	
	@XmlAttribute
	private int year;
	
	@XmlTransient
	// Source meta tag
	private String source;
	
	@XmlTransient
	private LiteratureReference next;
	
	private final static String DEFAULT_CHARSET = "UTF-8";
	
	public LiteratureReference(String title, int year) {
		this.setTitle(title);
		this.setYear(year);
		this.authors = new LinkedList<Author>();
	}
	
	/**
	 * Generates a new LiteratureReference object from the given XML representation.
	 * 
	 * @param xml XML Content
	 * @return Unmarshalled LiteratureReference or null on error
	 */
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
	
	/**
	 * Returns the XML representation of this LiteratureReference for storage in a CommunityMashup.
	 * @return XML representation
	 */
	public String marshal() {
		try {
			Marshaller marshaller = JAXBContext.newInstance(LiteratureReference.class).createMarshaller();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshaller.marshal(this, baos);
			
			return baos.toString(DEFAULT_CHARSET);
		}
		catch(Exception e) {
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
		if(firstname == null || lastname == null) return;
		
		firstname = Normalizer.normalize(firstname.trim(), Normalizer.Form.NFC);
		lastname = Normalizer.normalize(lastname.trim(), Normalizer.Form.NFC);
		
		if(lastname.length() > 0 && firstname.length() > 0 &&
				!this.authors.contains(new Author(firstname, lastname)))
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

	public LiteratureReference getNext() {
		return next;
	}
	
	
	/**
	 * Allows to assign one or more other LiteratureReference objects to this one.
	 * This feature is used by the grouping algorithm for synchronization.
	 * @param next
	 */
	public void setNext(LiteratureReference next) {
		if(this.next == null)
			this.next = next;
		else
			this.next.setNext(next);
	}
	
	/**
	 * Returns a semicolon-separated list of authors.
	 */
	public String getAuthorsString() {
		StringBuilder sb = new StringBuilder();
		
		if(this.getAuthors().size() > 0)
			sb.append(this.getAuthors().getFirst());
		else
			sb.append("Kein Autor");
		

		for(int i = 1; i < this.getAuthors().size(); i++) {
			sb.append("; ");
			sb.append(this.getAuthors().get(i));
		}
		
		return sb.toString();
	}
	
	/**
	 * Returns the String representation of the reference.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(this.getAuthorsString());		
		
		sb.append(": ");
		sb.append(this.getTitle());
		sb.append(" (");
		sb.append(this.getYear());
		sb.append(")");
		
		return sb.toString();
	}
	
}
