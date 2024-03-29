/**
 *
 * $Id$
 */
package org.sociotech.communitymashup.application.validation;

import java.util.Date;

import org.sociotech.communitymashup.application.OAuthClientScope;

/**
 * A sample validator interface for {@link org.sociotech.communitymashup.application.OAuthClientConfig}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OAuthClientConfigValidator {
	boolean validate();

	boolean validateType(String value);
	boolean validateRedirectionURL(String value);
	boolean validateName(String value);
	boolean validateDescription(String value);
	boolean validateClientID(String value);
	boolean validateClientSecret(String value);
	boolean validateCode(String value);
	boolean validateGrantType(String value);
	boolean validateRefreshToken(String value);
	boolean validateAccessToken(String value);
	boolean validateAccessTokenCreationDate(Date value);
	boolean validateAccessTokenExpirationDate(Date value);
	boolean validateForbiddenMetaTags(String value);
	boolean validateAllowedMetaTags(String value);
	boolean validateClientScope(OAuthClientScope value);
	boolean validateOAuthScopeLevel(Integer value);
}
