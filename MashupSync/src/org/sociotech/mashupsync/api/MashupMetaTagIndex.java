package org.sociotech.mashupsync.api;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.*;

/**
 * This class represents the output of the meta tag list of a CommunityMashup.
 * @author Jakob Huber
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="XMI", namespace = "http://www.omg.org/XMI")

public class MashupMetaTagIndex {
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name="MetaTag", namespace="http://data.cscm.communitymashup.de")
	public static class MashupMetaTag {
		@XmlRootElement(name="metaTagged")
		static class MashupMetaTagAssignment {
			@XmlAttribute
			private String type;
			
			@XmlAttribute
			private String href;
			
			public String getIdentifier() {
				return this.href.split("\\?ident=")[1];
			}
			
			public String getType() {
				return this.type;
			}
			
			public MashupMetaTagAssignment() {
				
			}
		}
		
		@XmlElement(name="metaTagged")
		private List<MashupMetaTagAssignment> entries;
		
		@XmlAttribute
		private String name;
		
		public String getName() {
			return this.name;
		}
		
		public List<String> getContentIdents() {
			LinkedList<String> idents = new LinkedList<>();
			
			for(MashupMetaTagAssignment a : entries) {
				idents.add(a.getIdentifier());
			}
			
			return idents;
		}
		
		
		public MashupMetaTag() {
			
		}		
	}
	
	@XmlElement(name="MetaTag") 
	public List<MashupMetaTag> metaTags;

	public List<MashupMetaTag> getMetaTags() {
		return this.metaTags;
	}
}
