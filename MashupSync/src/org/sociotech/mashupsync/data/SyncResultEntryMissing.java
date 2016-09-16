package org.sociotech.mashupsync.data;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

public class SyncResultEntryMissing extends SyncResultEntry {
	
	public SyncResultEntryMissing(String source,
			LiteratureReference representativeReference) {
		super(source, representativeReference);
	}
	
	public String toString() {
		return "In source " + this.source + ": " + this.reference + " is missing.";
	}

}
