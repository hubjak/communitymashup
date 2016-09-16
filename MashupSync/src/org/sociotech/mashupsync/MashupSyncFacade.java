package org.sociotech.mashupsync;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sociotech.mashupsync.api.MashupAPIWrapper;
import org.sociotech.mashupsync.data.MashupCitationIndex;
import org.sociotech.mashupsync.data.MashupItem;
import org.sociotech.mashupsync.data.MashupMetaTagIndex;
import org.sociotech.mashupsync.exceptions.EmptyMashupException;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;
import org.sociotech.mashupsync.sync.SyncMethod;

public class MashupSyncFacade {
	private MashupAPIWrapper api;
	private HashMap<String, MashupItem> itemIndex;	
	private SyncMethod method;

	
	public MashupSyncFacade() {
		this.api = new MashupAPIWrapper();
		
	}
	
	public MashupItem getItemByIdentifier(String identifier) {
		if(this.itemIndex == null) return null;
		return this.itemIndex.get(identifier);		
	}
	
	public void setSyncMethod(SyncMethod method) {
		this.method = method;
	}
	
	public void init(String baseUrl) throws Exception {
		api.setUrl(baseUrl);
		api.connect();
		
		if(api.getCitationIndex().getCitations().size() == 0)
			throw new EmptyMashupException();
	}
	
	
	public void synchronize(SyncConfiguration config) {		
		HashMap<String, LiteratureReference> contents = new HashMap<>();
		HashMap<String, List<LiteratureReference>> sources = new HashMap<>();
		
		for(MashupCitationIndex.MashupCitation item : api.getCitationIndex().getCitations()) {
			contents.put(item.getContentIdent(), LiteratureReference.fromXml(item.getCitationData()));
		}
		
		for(MashupMetaTagIndex.MashupMetaTag item : api.getMetaTagIndex().getMetaTags()) {
			if(config.containsSource(item.getName())) {
				LinkedList<LiteratureReference> refs = new LinkedList<>();
				
				for(String ident : item.getContentIdents())
					refs.add(contents.get(ident));
				
				sources.put(item.getName(), refs);
			}
		}
	}
}
