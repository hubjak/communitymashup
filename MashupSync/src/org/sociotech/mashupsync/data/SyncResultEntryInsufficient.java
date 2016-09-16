package org.sociotech.mashupsync.data;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

public class SyncResultEntryInsufficient extends SyncResultEntry {
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
	
	public String toString() {
		return "In source " + this.source + ": " + this.foundReference + " is missing authors.";
	}

}
