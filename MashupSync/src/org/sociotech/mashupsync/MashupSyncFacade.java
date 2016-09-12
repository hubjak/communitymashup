package org.sociotech.mashupsync;

import org.sociotech.mashupsync.api.MashupAPIWrapper;

public class MashupSyncFacade {
	MashupAPIWrapper api;
	
	public MashupSyncFacade() {
		this.api = new MashupAPIWrapper();
	}
	
	public void init(String baseUrl) throws Exception {
		api.setUrl(baseUrl);
		api.connect();
	}
}
