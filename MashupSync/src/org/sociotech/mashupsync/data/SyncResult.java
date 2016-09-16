package org.sociotech.mashupsync.data;

import java.util.LinkedList;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

public class SyncResult {
	LinkedList<SyncResultEntry> warnings;

	public LinkedList<SyncResultEntry> getWarnings() {
		return warnings;
	}

	public void setWarnings(LinkedList<SyncResultEntry> warnings) {
		this.warnings = warnings;
	}

	public SyncResult() {
		this.warnings = new LinkedList<SyncResultEntry>();
	}
}
