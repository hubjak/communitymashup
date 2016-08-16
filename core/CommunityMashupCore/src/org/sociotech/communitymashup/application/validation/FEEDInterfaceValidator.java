/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.application.validation;


/**
 * A sample validator interface for {@link org.sociotech.communitymashup.application.FEEDInterface}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FEEDInterfaceValidator {
	boolean validate();

	boolean validateAllowPersonFiltering(Boolean value);
	boolean validateAllowOrganisationFiltering(Boolean value);
	boolean validateAllowTypeFiltering(Boolean value);
	boolean validateAllowTagFiltering(Boolean value);
	boolean validateAllowMetaTagFiltering(Boolean value);
	boolean validateAllowCategoryFiltering(Boolean value);
	boolean validateLanguage(String value);
	boolean validateFeedType(String value);
	boolean validateFeedTitle(String value);
}
