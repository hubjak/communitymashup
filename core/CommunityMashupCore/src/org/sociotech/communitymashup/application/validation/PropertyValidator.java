/*******************************************************************************
 * Copyright (c) 2013 Peter Lachenmaier - Cooperation Systems Center Munich (CSCM).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Peter Lachenmaier - Design and initial implementation
 ******************************************************************************/
package org.sociotech.communitymashup.application.validation;

import org.sociotech.communitymashup.application.PropertyTypes;


/**
 * A sample validator interface for {@link org.sociotech.communitymashup.application.Property}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface PropertyValidator {
	boolean validate();

	boolean validateKey(String value);
	boolean validateValue(String value);

	boolean validateHidden(Boolean value);

	boolean validateChangeable(Boolean value);

	boolean validatePossibleValues(String value);

	boolean validateHelpText(String value);

	boolean validateRequired(Boolean value);

	boolean validatePropertyType(PropertyTypes value);
}
