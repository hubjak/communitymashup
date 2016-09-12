package org.sociotech.mashupsync.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

import javax.xml.bind.Element;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class MashupAPIWrapper {
	
	private String baseUrl;
	private MashupGenericIndex contentIndex;
	private MashupItem metaTagIndex;
	private MashupGenericIndex contents;
 
	private final static String TYPE_METATAG = "data:MetaTag",
			KEY_SOURCE_PREFIX = "org.sociotech.communitymashup.source.";
	
	public MashupAPIWrapper() {
		
	}
	
	public static String readUrl(String url) throws MalformedURLException, IOException {
		URL mashup = new URL(url);
		BufferedReader r = new BufferedReader(new InputStreamReader(mashup.openStream()));
		
		StringBuilder sb = new StringBuilder();
		String readln;

		while((readln = r.readLine()) != null)
			sb.append(readln);
		
		return sb.toString();
	}
	
	public void connect() throws Exception {
		if(this.baseUrl == null)
			throw new Exception("No Mashup base URL defined");
		
		String contents = readUrl(this.baseUrl + "/");
		Unmarshaller unmarshaller = JAXBContext.newInstance(MashupGenericIndex.class).createUnmarshaller();
		
		this.contentIndex = (MashupGenericIndex) unmarshaller.unmarshal(new StringReader(contents));
	}
	
	public void setUrl(String url) {
		this.baseUrl = url;
	}
	
	public String getUrl() {
		return this.baseUrl;
	}
}
