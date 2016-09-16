package org.sociotech.mashupsync.data;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="XMI", namespace = "http://www.omg.org/XMI")

public class MashupCitationIndex {
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name="Citation", namespace="http://data.cscm.communitymashup.de")
	public static class MashupCitation {
		@XmlRootElement(name="informationObjects")
		static class ContentEntry {
			@XmlAttribute
			private String type;
			
			@XmlAttribute
			private String href;
			
			public String getIdentifier() {
				return this.href.split("\\?ident=")[1];
			}
			
			public ContentEntry() {
				
			}
		}
		
		@XmlElement(name="informationObjects")
		private List<ContentEntry> entries;
		
		@XmlAttribute
		private String citationData;
		
		public String getCitationData() {
			return this.citationData;
		}
		
		public String getContentIdent() {
			if(this.entries.size() == 0) return null;
			
			return this.entries.get(0).getIdentifier();
		}
		
		
		public MashupCitation() {
			
		}		
	}
	
	@XmlElement(name="Citation") 
	public List<MashupCitation> citations;

	public List<MashupCitation> getCitations() {
		return this.citations;
	}
}
