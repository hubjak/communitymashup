/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.collection.validation;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.sociotech.communitymashup.data.Category;
import org.sociotech.communitymashup.data.MetaTag;
import org.sociotech.communitymashup.data.Organisation;
import org.sociotech.communitymashup.data.Person;
import org.sociotech.communitymashup.data.Tag;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.collection.SmartInformationObjectCollection}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SmartInformationObjectCollectionValidator {
	boolean validate();

	boolean validatePositiveTags(EList<Tag> value);
	boolean validateNegativeTags(EList<Tag> value);
	boolean validatePositiveMetaTags(EList<MetaTag> value);
	boolean validatePositiveCategories(EList<Category> value);
	boolean validatePositivePersons(EList<Person> value);
	boolean validateNegativeMetaTags(EList<MetaTag> value);
	boolean validateNegativeCategories(EList<Category> value);
	boolean validateNegativePersons(EList<Person> value);
	boolean validatePositiveOrganisations(EList<Organisation> value);
	boolean validateNegativeOrganisations(EList<Organisation> value);
	boolean validateIncludePersons(Boolean value);
	boolean validateIncludeOrganisations(Boolean value);
	boolean validateIncludeContents(Boolean value);
	boolean validateMinimumAge(Date value);
}
