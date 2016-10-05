package org.sociotech.mashupsync.normalization;

import java.text.Normalizer;

import org.sociotech.mashupsync.literaturereference.LiteratureReference;

/**
 * This normalization implementation is inspired by:
 * Steve Lawrence, C. Lee Giles, Kurt D. Bollacker: Autonomous Citation Matching (1999).
 * 
 * @author Jakob Huber
 *
 */

public class DefaultNormalizer implements NormalizationMethod {
	
	private final static String[] abbr_search = {
		"et al\\.", "conf\\.", "proc\\.", "intl\\.", "ß", "\\sund\\s", "^und", "und$", "&"
	};
	
	private final static String[] abbr_replace = {
		"et alii", "conference", "proceedings", "international", "ss", " and ", "and ", " and", " and "
	};
	
	@Override
	public String normalize(LiteratureReference reference) {
		// convert to lower case
		String tmp = reference.getTitle().toLowerCase();
		
		// Convert to UTF-8 normal form NFD
		tmp = Normalizer.normalize(tmp, Normalizer.Form.NFKD);
		
		// Expand common abbreviations
		for(int i = 0; i < abbr_search.length; i++)
			tmp = tmp.replaceAll(abbr_search[i], abbr_replace[i]);
		
		// Replace any kind of dashes with spaces
		tmp = tmp.replaceAll("\\p{Pd}", " ");
		
		// Replace slashes, pluses and stars with spaces
		tmp = tmp.replaceAll("[\\/\\\\+\\*]", " ");
		
		// Remove any remaining non-alphanumeric characters
		tmp = tmp.replaceAll("[^ a-zA-Z0-9]", "");
		
		// Reduce blocks of more than one space to a single space
		tmp = tmp.replaceAll("\\s{2,}", " ");
				
		// Trim and return
		return tmp.trim();
		
	}

}
