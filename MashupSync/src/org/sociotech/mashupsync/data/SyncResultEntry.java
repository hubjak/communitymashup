package org.sociotech.mashupsync.data;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

public class SyncResultEntry {	
		
		// The identifier of the source the reference is missing in
		protected String source;
		
		// The representative reference for the group
		protected LiteratureReference reference;
		
		public SyncResultEntry(String source, LiteratureReference reference) {
			this.setSource(source);
			this.setReference(reference);
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