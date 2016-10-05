package org.sociotech.mashupsync.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.sociotech.mashupsync.data.*;

public class MashupAPIWrapper {
	
	private String baseUrl;
	private MashupMetaTagIndex metaTagIndex;
	private MashupCitationIndex citationIndex;
 
	
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
			throw new IllegalStateException("No Mashup base URL defined");
		
		// fetch meta tag list
		String metaTagIndex = readUrl(this.baseUrl + "/getMetaTags");
		Unmarshaller unmarshaller = JAXBContext.newInstance(MashupMetaTagIndex.class).createUnmarshaller();
		this.metaTagIndex = (MashupMetaTagIndex) unmarshaller.unmarshal(new StringReader(metaTagIndex));
		
		// fetch citation list
		String citations = readUrl(this.baseUrl + "/getCitations");
		unmarshaller = JAXBContext.newInstance(MashupCitationIndex.class).createUnmarshaller();		
		this.citationIndex = (MashupCitationIndex) unmarshaller.unmarshal(new StringReader(citations));
	}
	
	public void setUrl(String url) {
		this.baseUrl = url;
	}
	
	public MashupCitationIndex getCitationIndex() {
		if(this.citationIndex == null)
			throw new IllegalStateException("Contents not yet loaded");
		
		return this.citationIndex;
	}
	
	public MashupMetaTagIndex getMetaTagIndex() {
		if(this.metaTagIndex == null)
			throw new IllegalStateException("Contents not yet loaded");
		
		return this.metaTagIndex;
	}
	
	public String getUrl() {
		return this.baseUrl;
	}
}
