package org.sociotech.mashupsync;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sociotech.mashupsync.api.MashupAPIWrapper;
import org.sociotech.mashupsync.data.MashupCitationIndex;
import org.sociotech.mashupsync.data.MashupItem;
import org.sociotech.mashupsync.data.MashupMetaTagIndex;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.exceptions.EmptyMashupException;
import org.sociotech.mashupsync.gui.ProgressListener;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;
import org.sociotech.mashupsync.normalization.DefaultNormalizer;
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
	
	/**
	 * Chooses the synchronization strategy.
	 * @param method Synchronization method
	 */
	public void setSyncMethod(SyncMethod method) {
		this.method = method;
	}
	
	/**
	 * Initializes the API Wrapper with the given URL and validates the CommunityMashup.
	 * @param baseUrl The CommunityMashup base URL
	 * @throws Exception
	 */
	public void init(String baseUrl) throws Exception {
		api.setUrl(baseUrl);
		api.connect();
		
		if(api.getCitationIndex() == null || api.getCitationIndex().getCitations().size() == 0)
			throw new EmptyMashupException();
	}	
	
	/**
	 * Initializes the data structures according to the configuration and
	 * starts the selected algorithm.
	 * 
	 * @param config Configuration of the synchronization (e.g. passed by GUI)
	 * @param progressListener The ProgressListener the algorithm should report the progress to (e.g. GUI).
	 * @return 
	 */
	public SyncResult synchronize(SyncConfiguration config, ProgressListener progressListener) {		
		if(this.method == null)
			throw new IllegalStateException("No sync algorithm selected.");
		
		HashMap<String, LiteratureReference> contents = new HashMap<>();
		HashMap<String, List<LiteratureReference>> sources = new HashMap<>();
		
		LiteratureReference ref;
		
		for(MashupCitationIndex.MashupCitation item : api.getCitationIndex().getCitations()) {
			ref = LiteratureReference.fromXml(item.getCitationData());
			
			if(ref.getYear() >= config.getMinYear())
				contents.put(item.getContentIdent(), ref);
		}
		
		for(MashupMetaTagIndex.MashupMetaTag item : api.getMetaTagIndex().getMetaTags()) {
			if(config.containsSource(item.getName())) {
				LinkedList<LiteratureReference> refs = new LinkedList<>();
				
				for(String ident : item.getContentIdents())
					if(contents.get(ident) != null) {
						refs.add(contents.get(ident));
						contents.get(ident).setSource(item.getName());
					}
				
				sources.put(item.getName(), refs);
			}
		}
		
		if(progressListener == null)
			return this.method.synchronize(contents, sources, config);
		else
			return this.method.synchronize(contents, sources, config, progressListener);
	}
}
