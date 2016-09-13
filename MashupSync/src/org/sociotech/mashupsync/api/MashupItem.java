package org.sociotech.mashupsync.api;

import java.util.List;

import javax.xml.bind.annotation.*;


@XmlRootElement(name="items")
@XmlAccessorType(XmlAccessType.FIELD)
public class MashupItem {

	private final static String TYPE_METATAG = "data:MetaTag",
			TYPE_CONTENT = "data:Content",
			KEY_SOURCE_PREFIX = "org.sociotech.communitymashup.source.";
	
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


	public MashupItem() {
		
	}
	
	
}
