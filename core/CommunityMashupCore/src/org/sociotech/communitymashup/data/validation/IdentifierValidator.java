/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.data.validation;

import org.sociotech.communitymashup.data.Item;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.data.Identifier}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface IdentifierValidator {
	boolean validate();

	boolean validateKey(String value);
	boolean validateValue(String value);
	boolean validateIdentified(Item value);
}
