package org.sociotech.mashupsync.sync;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.sociotech.mashupsync.SyncConfiguration;
import org.sociotech.mashupsync.data.SyncResult;
import org.sociotech.mashupsync.data.SyncResultEntryInsufficient;
import org.sociotech.mashupsync.data.SyncResultEntryMissing;
import org.sociotech.mashupsync.gui.ProgressListener;
import org.sociotech.mashupsync.literaturereference.LiteratureReference;
import org.sociotech.mashupsync.normalization.DefaultNormalizer;
import org.sociotech.mashupsync.normalization.NormalizationMethod;

import java.text.Normalizer;


/**
 * This sync algorithm implementation is partly based on concepts described in:
 * Steve Lawrence, C. Lee Giles, Kurt D. Bollacker: Autonomous Citation Matching (1999).
 * 
 * The described concepts of grouping and defining representatives for similar citations
 * were applied to CommunityMashup content objects.
 * 
 * @author Jakob Huber
 *
 */

public class NormalizeAndGroup implements SyncMethod {
	private ProgressListener progressListener;
	private NormalizationMethod normalizer;

	public NormalizeAndGroup() {
		this.normalizer = new DefaultNormalizer();
	} 

	@Override
	public SyncResult synchronize(
			HashMap<String, LiteratureReference> contents,
			HashMap<String, List<LiteratureReference>> sources,
			SyncConfiguration config) {
		
		HashMap<String, LinkedList<LiteratureReference>> map = new HashMap<>();
		
		// Sort references descending by number of authors
		List<LiteratureReference> sorted = new LinkedList<LiteratureReference>(contents.values());
		
		int sizeTotal = sorted.size();
		int itemsProcessed = 0;
		
		Collections.sort(sorted, new Comparator<LiteratureReference>() {
			@Override
			public int compare(LiteratureReference r1, LiteratureReference r2) {
				return new Integer(r2.getAuthors().size()).compareTo(r1.getAuthors().size());
			}			
		});
		
		System.out.println("Abzuarbeiten: " + sorted.size());
		
		int matches = 0;
		
		mainLoop: for(LiteratureReference ref : sorted) {
			String normalizedTitle = ref.getYear() + " " + this.normalizer.normalize(ref);
			List<LiteratureReference> entries = map.get(normalizedTitle);
			
			if(this.progressListener != null)
				this.progressListener.reportProgress(itemsProcessed++, sizeTotal);
			
			if(entries == null) {
				// ref is the first reference with this title
				
				
				// In personal mode, check if the publication matches the configured name
				if(config.getSyncMode() == SyncConfiguration.Mode.PERSONAL && 
						!containsAuthor(ref, config.getFirstName(), config.getLastName()))
						continue;
				
				map.put(normalizedTitle, new LinkedList<LiteratureReference>());
				
				// This reference must be the one with the most complete list of authors out of all
				// references with the same title, so we set it as a
				// representative of the group.
				map.get(normalizedTitle).add(ref);
			} else {
				// Another publication with this title exists
				
				// Check if it fits into some existing group
				for(LiteratureReference cmp : entries) {
					if(isAuthorsMatch(cmp, ref)) {
						// If so, add it to the existing group.
						cmp.setNext(ref);
						continue mainLoop;
					} else {
						System.out.println("====== NO MATCH =====");
						System.out.println(cmp.getAuthorsString());
					}
				}
				
				// If not, the titles are probably only coincidentally equal - a new group is required
				
				System.out.println(ref.getAuthorsString());
				System.out.println("=======================================\n\n\n");
				// In personal mode, check if the publication matches the configured name
				if(config.getSyncMode() == SyncConfiguration.Mode.PERSONAL && 
						!containsAuthor(ref, config.getFirstName(), config.getLastName()))
						continue;
				
				// If so, create the new group
				entries.add(ref);
			}
		}
		
		SyncResult result = new SyncResult();
		
		// Auxiliary data structure to efficiently track the missing entries
		HashMap<String, Integer> tmp = new HashMap<String, Integer>();
		
		for(String source : sources.keySet()) {
			tmp.put(source, -1);
		}
				
		int i = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		
		for(LinkedList<LiteratureReference> list : map.values()) {
			m++;
			
			for(LiteratureReference ref : list) {
				LiteratureReference rep = ref;
				
				do {
					tmp.put(ref.getSource(), i);
					
					if(rep != ref && ref.getAuthors().size() < rep.getAuthors().size()) {
						result.getWarnings().add(new SyncResultEntryInsufficient(ref.getSource(), rep, ref));
					}
				} while((ref = ref.getNext()) != null);
				
				int j = 0;

				for(String source : sources.keySet()) {
					
					if(tmp.get(source) != i) {
						result.getWarnings().add(new SyncResultEntryMissing(source, rep));
						j++;
					}
					
				}
				
				if(j == 0) k++;
				else if(j == 1) l++;
				
				i++;
			}
			
		}
		
		System.out.println("In allen " + k);
		System.out.println("In zwei " + l);
		System.out.println("Gesamt " + i);
		System.out.println("Titel " + m);
		System.out.println("Matches " + matches);
		
		return result;
	}
	
	/**
	 * Checks if the authors of two given references are matching.
	 * In this implementation, a match is given if the authors' list of the 
	 * reference in question is a prefix of the authors' list of the existing master reference.
	 * 
	 * @param existing The existing literature reference
	 * @param check The literature reference in question
	 * @return boolean
	 */
	private static boolean isAuthorsMatch(LiteratureReference existing, LiteratureReference check) {
		int i = 0;
		
		for(LiteratureReference.Author ac : check.getAuthors()) {
			if(!isSamePerson(existing.getAuthors().get(i).getFirstName(), existing.getAuthors().get(i).getLastName(),
					ac.getFirstName(), ac.getLastName())) return false;
			
			i++;
		}
		
		return true;
	}
	
	/**
	 * Checks if two pairs of first-/lastnames are considered to refer to the same person.
	 * Abbreviations, unicode normal forms and multiple firstnames are considered.
	 * 
	 * @param firstname1 First name of the first person
	 * @param lastname1 Last name of the first person
	 * @param firstname2 First name of the second person
	 * @param lastname2 Last name of the second person
	 * @return boolean
	 */
	private static boolean isSamePerson(String firstname1, String lastname1, String firstname2, String lastname2) {
		return new LiteratureReference.Author(firstname1, lastname1).isSamePerson(firstname2, lastname2);
	}
	
	/**
	 * Checks if a given literature reference contains an author with the given name
	 * according to the isSamePerson method.
	 * @param ref The literature reference to search for the author
	 * @param firstname The author's first name
	 * @param lastname The author's last name
	 * @return boolean
	 */
	private static boolean containsAuthor(LiteratureReference ref, String firstname, String lastname) {
		for(LiteratureReference.Author a : ref.getAuthors()) {
			if(isSamePerson(a.getFirstName(), a.getLastName(), firstname, lastname))
				return true;
		}
		
		return false;
	}

	@Override
	public SyncResult synchronize(
			HashMap<String, LiteratureReference> contents,
			HashMap<String, List<LiteratureReference>> sources,
			SyncConfiguration config, ProgressListener progressListener) {
		this.progressListener = progressListener;
		
		return this.synchronize(contents, sources, config);
	}
	
}
