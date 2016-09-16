package org.sociotech.communitymashup.source.researchgate.apiwrapper.items;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Publication {
	// 2016-01-01T00:00:00.000+0000
	private final static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);

	
	private String id;	
	private String title;	
	private String abstractText;
	private Date creationDate;
	private String type;
	
	// Additional meta tags extracted from the website for future use
	private HashMap<String, String> metaTags;
	private LinkedList<Author> authors;	
	
	public Publication(String id, String title) {
		this.setId(id);
		this.setTitle(title);
		this.authors = new LinkedList<Author>();
		this.metaTags = new HashMap<String, String>();
	}
	
	public void addTag(String key, String value) {
		this.metaTags.put(key, value);
	}
	
	public String getTag(String key) {
		return this.metaTags.get(key);
	}
	
	public void addAuthor(String firstname, String lastname, String id) {
		this.authors.add(new Author(firstname, lastname, id));
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(String dateString) {		
		try {
			this.creationDate = DATE_FORMAT.parse(dateString);
		} catch(Exception e) {e.printStackTrace();};
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public LinkedList<Author> getAuthors() {
		return authors;
	}
	
	public void setAuthors(LinkedList<Author> authors) {
		this.authors = authors;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Publication && ((Publication) o).getId().equals(getId());
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

}
