package org.sociotech.mashupsync;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sociotech.mashupsync.api.MashupAPIWrapper;
import org.sociotech.mashupsync.api.MashupItem;
import org.sociotech.mashupsync.exceptions.EmptyMashupException;

public class MashupSyncFacade {
	private MashupAPIWrapper api;
	private HashMap<String, MashupItem> itemIndex;	
	private HashMap<String, List<MashupItem>> sourceIndex;
	private List<String> sources;

	
	public MashupSyncFacade() {
		this.api = new MashupAPIWrapper();
		
	}
	
	public MashupItem getItemByIdentifier(String identifier) {
		if(this.itemIndex == null) return null;
		return this.itemIndex.get(identifier);		
	}
	
	public void init(String baseUrl) throws Exception {
		api.setUrl(baseUrl);
		api.connect();
		
		if(api.getItems() == null || api.getItems().size() == 0)
			throw new EmptyMashupException();
		
		this.itemIndex = new HashMap<String, MashupItem>();
	}
	
	
	public void synchronize(SyncConfiguration config) {
		if(this.itemIndex == null)
			throw new IllegalStateException();
		
		for(MashupItem item : api.getItems()) {
			if(item.isContent()) {
				this.itemIndex.put(item.getIdent(), item);
			} else if(item.isMetaTag() && config.containsSource(item.getName())) {
				
			}
		}
	}
}
