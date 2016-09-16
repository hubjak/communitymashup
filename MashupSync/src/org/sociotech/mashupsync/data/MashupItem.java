package org.sociotech.mashupsync.data;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlRootElement(name="items")
@XmlAccessorType(XmlAccessType.FIELD)
public class MashupItem {

	private final static String TYPE_METATAG = "data:MetaTag",
			TYPE_CONTENT = "data:Content",
			TYPE_CITATION = "data:Citation";
	
	@XmlAttribute
	private String ident;
	
	@XmlAttribute(namespace = "http://www.w3.org/2001/XMLSchema-instance")
	private String type;
	
	@XmlAttribute
	private String key;
	
	@XmlAttribute
	private String metaTagged;
	
	@XmlAttribute
	private String stringValue;
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute
	private String citationData;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdent() {
		return ident;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMetaTagged() {
		return metaTagged;
	}

	public void setMetaTagged(String metaTagged) {
		this.metaTagged = metaTagged;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public boolean isMetaTag() {
		return this.getType().equals(TYPE_METATAG);
	}
	
	public boolean isContent() {
		return this.getType().equals(TYPE_CONTENT);
	}
	
	public boolean isCitation() {
		return this.getType().equals(TYPE_CITATION);
	}
	
	public String getCitationData() {
		return this.citationData;
	}
	
	public void setCitationData(String citationData) {
		this.citationData = citationData;
	}


	public MashupItem() {
		
	}
	
	
}
