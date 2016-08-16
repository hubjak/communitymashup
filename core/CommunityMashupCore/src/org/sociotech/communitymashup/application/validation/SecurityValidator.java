/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.application.validation;

import org.sociotech.communitymashup.application.Interface;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.application.Security}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SecurityValidator {
	boolean validate();

	boolean validateInterface(Interface value);
}
