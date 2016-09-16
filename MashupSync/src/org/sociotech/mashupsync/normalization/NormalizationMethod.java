package org.sociotech.mashupsync.normalization;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * Normalizer classes are given a LiteratureReference object from which a
 * normalized string will be generated. The string should wipe out
 * as many minor spelling differences as possible in order to enable
 * reliable comparisons across platforms.
 * 
 * @author Jakob Huber
 *
 */

public interface NormalizationMethod {
	
	public String normalize(LiteratureReference reference);
	
}
