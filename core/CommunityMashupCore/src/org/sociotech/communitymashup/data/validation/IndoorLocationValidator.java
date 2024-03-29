/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.data.validation;

import org.eclipse.emf.common.util.EList;

import org.sociotech.communitymashup.data.IndoorLocation;
import org.sociotech.communitymashup.data.Location;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.data.IndoorLocation}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface IndoorLocationValidator {
	boolean validate();

	boolean validateLocation(Location value);
	boolean validateParentIndoorLocation(IndoorLocation value);
	boolean validateIndoorLocations(EList<IndoorLocation> value);
	boolean validateName(String value);
}
