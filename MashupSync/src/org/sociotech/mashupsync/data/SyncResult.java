package org.sociotech.mashupsync.data;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
/**
 * A SyncResult contains multiple SyncResultEntries which represent absence of 
 * or data insufficiency in the literature reference inventory of a certain source
 * @author Jakob Huber
 *
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class SyncResult {
	@XmlAnyElement(lax=true)
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

	public String toXML() {
		try {
			Marshaller marshaller = JAXBContext.newInstance(SyncResult.class, SyncResultEntry.class, SyncResultEntryInsufficient.class,
					SyncResultEntryMissing.class).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			marshaller.marshal(this, baos);
			
			return baos.toString("UTF-8");
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String toCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"Quelle\",\"Titel\",\"Autoren\",\"Jahr\",\"Inkonsistenz\"\n");
		
		for(SyncResultEntry e : this.warnings) {
			sb.append("\"" + e.getSource() + "\",");
			sb.append("\"" + e.getReference().getTitle().replace("\"", "\"\"") + "\",");
			sb.append("\"" + e.getReference().getAuthorsString().replace("\"", "\"\"") + "\",");
			sb.append("\"" + e.getReference().getYear() + "\",");
			
			if(e instanceof SyncResultEntryInsufficient) {
				sb.append("\"Fehlende Autoren (verglichen mit Quelle " + e.getReference().getSource() + "): " + 
						String.join("; ", ((SyncResultEntryInsufficient) e).getMissingAuthorsNames()) + "\"\n");
			} else if(e instanceof SyncResultEntryMissing) {
				sb.append("\"Kein übereinstimmender Eintrag in der Quelle\"\n");
			}
		}
		
		return sb.toString();
	}
}
