package org.sociotech.mashupsync.sync;
import java.util.HashMap;
import java.util.List;

import org.sociotech.mashupsync.SyncConfiguration;
import org.sociotech.mashupsync.data.MashupItem;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.gui.ProgressListener;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;

public interface SyncMethod {
	public SyncResult synchronize(HashMap<String, LiteratureReference> contents, 
			HashMap<String, List<LiteratureReference>> sources, SyncConfiguration config);
	
	public SyncResult synchronize(HashMap<String, LiteratureReference> contents, 
			HashMap<String, List<LiteratureReference>> sources, SyncConfiguration config, ProgressListener progressListener);
}
