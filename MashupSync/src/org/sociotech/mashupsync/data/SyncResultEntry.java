package org.sociotech.mashupsync.data;

import javax.xml.bind.annotation.*;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * Base class of a SyncResultEntry which represents a data inconsistency 
 * detected by a synchronization algorithm.
 * @author Jakob Huber
 *
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class SyncResultEntry {	
		
	// The identifier of the source the reference is missing or incomprehensive in
	@XmlTransient
	protected String source;
	
	// The representative reference for the group
	@XmlElement
	protected LiteratureReference reference;
	
	public SyncResultEntry(String source, LiteratureReference reference) {
		this.setSource(source);
		this.setReference(reference);
	}
	
	public SyncResultEntry() {
		
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LiteratureReference getReference() {
		return reference;
	}

	public void setReference(LiteratureReference reference) {
		this.reference = reference;
	}
}