package org.sociotech.mashupsync.data;

import javax.xml.bind.annotation.*;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SyncResultEntryMissing extends SyncResultEntry {
	
	public SyncResultEntryMissing(String source,
			LiteratureReference representativeReference) {
		super(source, representativeReference);
	}
	
	public SyncResultEntryMissing() {
		super();
	}
	
	@XmlAttribute(name="representativeSource")
	public String getRepresentativeSource() {
		return this.reference.getSource();
	}
	
	public String toString() {
		return "In source " + this.source + ": " + this.reference + " is missing.";
	}

}
