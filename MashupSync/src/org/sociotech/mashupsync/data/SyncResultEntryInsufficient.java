package org.sociotech.mashupsync.data;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.*;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SyncResultEntryInsufficient extends SyncResultEntry {
	@XmlElement
	private LiteratureReference foundReference;
	
	public SyncResultEntryInsufficient(String source,
			LiteratureReference representativeReference, LiteratureReference foundReference) {
		super(source, representativeReference);
		this.setFoundReference(foundReference);
	}

	public LiteratureReference getFoundReference() {
		return foundReference;
	}

	public void setFoundReference(LiteratureReference foundReference) {
		this.foundReference = foundReference;
	}
	
	public SyncResultEntryInsufficient() {
		
	}
	
	@XmlAttribute(name="foundSource")
	public String getFoundSource() {
		return this.foundReference.getSource();
	}
	
	@XmlAttribute(name="representativeSource")
	public String getCompleteSource() {
		return this.reference.getSource();
	}
	
	/**
	 * Returns a list of authors contained in the representativeReference who
	 * are missing in the foundReference.
	 * 
	 * @return LinkedList of missing authors
	 */
	public LinkedList<LiteratureReference.Author> getMissingAuthors() {
		LinkedList<LiteratureReference.Author> authors = new LinkedList<>(reference.getAuthors());
		authors.removeAll(foundReference.getAuthors());
		
		return authors;
	}
	
	/**
	 * Returns a list of authors' names contained in the representativeReference who
	 * are missing in the foundReference.
	 * 
	 * @return LinkedList of missing authors
	 */
	public List<String> getMissingAuthorsNames() {
		LinkedList<String> names = new LinkedList<>();
		
		for(LiteratureReference.Author a : this.getMissingAuthors()) {
			names.add(a.toString());
		}
		
		return names;
	}
	
	public String toString() {
		return "In source " + this.source + ": " + this.foundReference + " is missing authors.";
	}


}
