package org.sociotech.mashupsync.sync;
import java.util.HashMap;
import java.util.List;

import org.sociotech.mashupsync.SyncConfiguration;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.gui.ProgressListener;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * Interface for synchronization algorithms.
 * 
 * @author Jakob Huber
 *
 */

public interface SyncMethod {
	/**
	 * This method performs the synchronization of the given contents according to a given configuration.
	 * 
	 * @param contents HashMap mapping CommunityMashup identifiers (e.g. "a_1") to LiteratureReference objects
	 * @param sources HashMap mapping CommunityMashup source meta tags to a list of LiteratureReferenceObjects
	 * @param config The configuration of the algorithm
	 * @return SyncResult The synchronization result object representing several inconsistencies 
	 * detected by the algorithm
	 */
	public SyncResult synchronize(HashMap<String, LiteratureReference> contents, 
			HashMap<String, List<LiteratureReference>> sources, SyncConfiguration config);
	
	/**
	 * This method performs the synchronization of the given contents according to a given configuration.
	 * Provides additional support for a ProgressListener in order to track the progress of the algorithm at runtime
	 * (i.e. in GUI applications)
	 * 
	 * @param contents HashMap mapping CommunityMashup identifiers (e.g. "a_1") to LiteratureReference objects
	 * @param sources HashMap mapping CommunityMashup source meta tags to a list of LiteratureReferenceObjects
	 * @param config The configuration of the algorithm
	 * @param progressListener An object which the algorithm will report the current progress to during each step
	 * @return SyncResult The synchronization result object representing several inconsistencies 
	 * detected by the algorithm
	 */
	public SyncResult synchronize(HashMap<String, LiteratureReference> contents, 
			HashMap<String, List<LiteratureReference>> sources, SyncConfiguration config, ProgressListener progressListener);
}
