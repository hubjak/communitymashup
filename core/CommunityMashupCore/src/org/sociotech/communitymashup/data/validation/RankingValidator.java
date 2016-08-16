/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.data.validation;

import java.util.Date;

import org.sociotech.communitymashup.data.Person;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.data.Ranking}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface RankingValidator {
	boolean validate();

	boolean validateDate(Date value);
	boolean validateRanker(Person value);
}
