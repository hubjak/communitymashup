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
/**
 * 
 */
package org.sociotech.communitymashup.source.instantiation.facade;

import org.osgi.framework.BundleContext;
import org.sociotech.communitymashup.source.facade.SourceServiceFacade;

/**
 * Every Source Bundle needs to provide a Service providing these interface. The
 * Service must be able to instantiate concrete source services for usage in
 * mashup configurations.
 * 
 * @author Peter Lachenmaier
 * 
 */
public interface SourceInstantiationFacade {

	/**
	 * Instantiates a new source service and returns the instantiated service.
	 * 
	 * @return The instantiated source service.
	 */
	public SourceServiceFacade instantiate();

	/**
	 * Sets the given bundle context for internal usage.
	 * 
	 * @param context Bundle context.
	 */
	public void setContext(BundleContext context);

}
