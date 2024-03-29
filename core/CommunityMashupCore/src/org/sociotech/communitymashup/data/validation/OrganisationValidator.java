/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.data.validation;

import org.eclipse.emf.common.util.EList;

import org.sociotech.communitymashup.data.Organisation;
import org.sociotech.communitymashup.data.Person;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.data.Organisation}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OrganisationValidator {
	boolean validate();

	boolean validateParentOrganisation(Organisation value);
	boolean validateLeader(Person value);
	boolean validateParticipants(EList<Person> value);
	boolean validateOrganisations(EList<Organisation> value);
}
