package org.sociotech.communitymashup.source.researchgate.apiwrapper.items;


import java.util.List;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class PublicationList {
	@XmlElement
	List<Publication> contents;
	
	public PublicationList() {
		
	}

	public List<Publication> getContents() {
		return contents;
	}

	public void setContents(List<Publication> contents) {
		this.contents = contents;
	}
}
