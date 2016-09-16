package org.sociotech.mashupsync.normalization;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * This normalization implementation is inspired by:
 * Steve Lawrence, C. Lee Giles, Kurt D. Bollacker: Autonomous Citation Matching (1999).
 * 
 * @author Jakob Huber
 *
 */

public class DefaultNormalizer implements NormalizationMethod {

	@Override
	public String normalize(LiteratureReference reference) {
		String tmp = reference.getTitle().toLowerCase();
	}

}
